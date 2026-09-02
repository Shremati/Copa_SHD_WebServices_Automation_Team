# AGENTS.md

## Project Snapshot
- This repo is a TestNG + RestAssured SOAP API automation suite for SHD airline services (mostly WAVE3 modules).
- Primary code lives under `src/test/java`; this is test-first automation, not an app with `src/main/java`.
- Service coverage is organized by module in `src/test/java/MODULES/WAVE3/*` (for example `Availability`, `CreateBookingService`, `DisplayBookingService`).

## Architecture And Execution Flow
- Test execution starts from a TestNG suite XML in `src/test/java/TestDriver/*.xml`.
- Maven Surefire is configured to run `src/test/java/TestDriver/${module}.xml` in `pom.xml`, with `module=testSuite` by default.
- Each module class (example: `src/test/java/MODULES/WAVE3/Availability/Availability.java`) is a thin orchestrator:
  - `@Test` methods named by scenario ID (`AVS_01`, `CBS_23`, etc.).
  - each method calls a corresponding `API_Tests/*.Execute()` static method.
- API test classes (example: `src/test/java/MODULES/WAVE3/Availability/API_Tests/Regular_availability_with_defaults.java`) do the main flow:
  1) mutate request XML, 2) send SOAP request, 3) assert response, 4) persist response artifacts.

## Data, Payload, And Response Pattern
- Endpoints and key file paths are centralized in `src/test/java/frameworkconstants/FrameworkConstants.java`.
- Request templates are under `src/test/java/XMLRequestDirectory/WAVE3/<Service>/`.
- XML updates are done through `src/test/java/GENERICS/XMLParser.java`, writing to `src/test/java/GENERICS/Temp_Request.xml`.
- Test data comes from Excel: `src/test/java/TestData/Scenario_TestData.xlsx` (plus flight data in `FlightNumbers.xlsx`).
- Responses are written to `src/test/java/SOAPResponses/WAVE3/<Service>/...xml`.

## Reporting And Listeners
- `listeners.TestListener` is wired in suite XML and module classes (`@Listeners`).
- Extent reporting pipeline: `src/test/java/reports/ExtentReport.java` + `src/test/java/reports/ExtentLogger.java`, output folder pattern `COPA_yyyyMMdd/`.
- Allure integration is active via `AllureRestAssured` filters in request specs and `allure-results/` artifacts.
- Suite end writes an Excel summary (`TestResults.xlsx`) through `src/test/java/reports/ExcelUtil.java`.

## Developer Workflows (Observed)
- Default run: `mvn test`
- Run a specific TestNG suite by Maven property:
  - `mvn test -Dmodule=sanitySuite`
  - `mvn test -Dmodule=sanitySuite_6Modules`
- Switch active classes/methods by editing `src/test/java/TestDriver/*.xml` include/comment blocks.
- Cleanup generated artifacts with `src/test/java/DataCleaner/DataClean.java` (deletes response/report/allure/target outputs).

## Project-Specific Conventions To Follow
- Keep scenario IDs in method names/descriptions aligned (`AVS_*`, `CBS_*`, etc.); this drives readability in reports.
- Preserve the existing static `Execute()` pattern for API scenario classes.
- Maintain Windows-style relative paths (e.g., `.\\src\\test\\java\\...`) already used in constants/utilities.
- If adding a new scenario, add all four pieces consistently:
  1) XML template in `XMLRequestDirectory`, 2) API test class in `API_Tests`, 3) module `@Test` wrapper, 4) suite XML inclusion.
- Be careful with shared mutable files (`Temp_Request.xml`) and global maps (`FrameworkConstants.availableFlights`) if introducing parallelism.

## Integration Boundaries / External Systems
- SOAP endpoints are environment URLs in `FrameworkConstants` (`SharesCURL`, `UAT`, etc.); `getBaseURL()` currently returns `SharesCURL`.
- Many tests depend on live SHD services and real route/flight availability (see retry loops in CreateBooking API tests).
- Post-check flows exist in some modules (for example `src/test/java/MODULES/WAVE3/CreateBookingService/PostCheck/*`) and can write back to Excel test data.
