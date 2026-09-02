# CBS_02 Test Workflow (Step by Step)

## What this test is
`CBS_02` is the CreateBooking scenario: **"1 segment, 1 passenger, stored fare, 1 telephone and ticketing"**.

- Wrapper method: `src/test/java/MODULES/WAVE3/CreateBookingService/CreateBookingService.java`
- Core flow: `src/test/java/MODULES/WAVE3/CreateBookingService/API_Tests/create_booking_1seg_1pax_stored_fare_1telephone_ticketing.java`
- Post-check ticketing flow: `src/test/java/MODULES/WAVE3/CreateBookingService/PostCheck/IssueTicket_create_booking_1seg_1pax_stored_fare_1telephone_ticketing.java`

## Step-by-step runtime flow
1. **Module setup runs first (constructor)**
   - `CreateBookingService()` creates response folders.
   - Calls `FlightBooking.bookFlight("CreateBookingService")`.
   - This builds `FrameworkConstants.availableFlights` by:
     - reading routes needed by the `CreateBookingService` sheet in `Scenario_TestData.xlsx`, then
     - loading candidate flight numbers per route from `src/test/java/TestData/FlightNumbers.xlsx` (sheet `Flight Data`).

2. **Test method entry**
   - `CBS_02()` calls:
     - `create_booking_1seg_1pax_stored_fare_1telephone_ticketing.Execute()`.

3. **Retry loop starts (max 5 attempts)**
   - In `Execute()`, variables:
     - `flightFound = false`
     - `i = 0`
   - Loop logic: `do { ... } while (!flightFound)`.

4. **Payload is updated for current attempt (`UpdatePayload(i)`)**
   - Reads row 1 from Excel sheet `CreateBookingService` in `Scenario_TestData.xlsx`.
   - Uses XML template:
     - `src/test/java/XMLRequestDirectory/WAVE3/CreateBookingService/create_booking_1seg_1pax_stored_fare_1telephone_ticketing.xml`
   - Writes into `Temp_Request.xml` using `XMLParser`:
     - `DepartureDateTime` from Excel
     - `DepartureAirport` and `ArrivalAirport` from Excel
     - `TicketTimeLimit` from Excel
     - `FlightNumber` from `availableFlights.get("<origin>-<destination>").get(i)`

5. **SOAP request is sent**
   - Endpoint: `getBaseURL() + getCreatebookingservice()` from `FrameworkConstants`.
   - Current base URL resolves to `SharesCURL`.
   - Request/response are logged to Extent + Allure.

6. **Success check per attempt**
   - If response contains both `Success` and `BookingReferenceID`, set `flightFound = true` and stop retrying.
   - Else increment `i` and try next flight.
   - If `i > 4`, fail test with: `No flights are having seats`.

7. **Only when flightFound = true (not reached in your failed run)**
   - Persist create-booking response XML under:
     - `src/test/java/SOAPResponses/WAVE3/CreateBookingService/...`
   - Validate response content (`Success`, `BookingReferenceID`, `Telephone`, `BaseFare`, no warnings, response time).
   - Write PNR to Excel column index 17.
   - Run post-check `IssueTicket...run()`:
     - sets record locator from Excel
     - calls Ticketing service
     - asserts `Success` + `TicketInfo`
     - writes ticket number to Excel column index 18.

## Flights checked by CBS_02 in your captured run
From Allure result `allure-results/875d6965-f21d-4c13-90cb-2a910ac7f788-result.json`, request attachments show route `PTY -> MIA` and these flight numbers (in order):

1. `226` (`.../2151d63a-ca0b-4488-b790-d8fb76b5a1d7-attachment.html`)
2. `419` (`.../6272360d-b4f4-4a1b-97e6-9405f7c9b6e5-attachment.html`)
3. `334` (`.../39b17f4b-86de-49dd-ad73-d4800a78e771-attachment.html`)
4. `485` (`.../db71c406-6b32-4c69-8b3b-c5f73ac5cb57-attachment.html`)
5. `240` (`.../c241e09e-bf4a-4c91-aec8-fd12fec8e89e-attachment.html`)

Because none returned both `Success` + `BookingReferenceID`, the test ended with `No flights are having seats`.

## Note about current source state
In the currently attached `CreateBookingService.java`, `CBS_02()` is commented out. The flow above is based on the `CBS_02` implementation and Allure evidence from earlier executions where it was enabled.

## Flowchart (Start to End)
```mermaid
flowchart TD
    A([Start]) --> B[CreateBookingService constructor]
    B --> C[createFolders for CreateBookingService responses]
    C --> D[FlightBooking.bookFlight("CreateBookingService")]
    D --> E[Build availableFlights from Scenario_TestData.xlsx + FlightNumbers.xlsx]
    E --> F[CBS_02 test method entry]
    F --> G[Call create_booking_1seg_1pax_stored_fare_1telephone_ticketing.Execute()]
    G --> H[Initialize loop: flightFound=false, i=0]
    H --> I[UpdatePayload(i): set date, route, ticket time limit, flight number]
    I --> J[Send CreateBooking SOAP request]
    J --> K{Response contains\nSuccess AND BookingReferenceID?}

    K -- Yes --> L[flightFound=true, exit retry loop]
    L --> M[Write CreateBooking response XML]
    M --> N[Run assertions: Success, BookingReferenceID, Telephone, BaseFare, warnings, response time]
    N --> O[Write PNR to Scenario_TestData.xlsx cell index 17]
    O --> P[Run post-check IssueTicket...run()]
    P --> Q[Update ticketing request with PNR]
    Q --> R[Send Ticketing SOAP request]
    R --> S[Assert Success + TicketInfo]
    S --> T[Write TicketNumber to Scenario_TestData.xlsx cell index 18]
    T --> U([End - Pass])

    K -- No --> V[i = i + 1]
    V --> W{i > 4?}
    W -- No --> I
    W -- Yes --> X[Assert.fail("No flights are having seats")]
    X --> Y([End - Fail])
```

## Business Flowchart (Simplified)
```mermaid
flowchart TD
    A([Start CBS_02]) --> B[Load route and candidate flights for requested market]
    B --> C[Try booking with first available candidate flight]
    C --> D{Booking created?\n(Success + BookingReferenceID)}

    D -- Yes --> E[Store booking reference (PNR)]
    E --> F[Issue ticket for created booking]
    F --> G{Ticket issued successfully?}
    G -- Yes --> H[Store ticket number]
    H --> I([End - Pass])

    D -- No --> J[Try next candidate flight]
    J --> K{More flights left\n(max 5 attempts)?}
    K -- Yes --> C
    K -- No --> L([End - Fail: No flights with seats])

    G -- No --> M([End - Fail: Ticketing failed])
```

## Management Summary (One-Line Strip)
`Route+Flight Pool Loaded` -> `Try Create Booking (max 5 flights)` -> `If booking succeeds, store PNR and issue ticket` -> `Pass with ticket number` / `Fail on no-seats or ticketing error`

