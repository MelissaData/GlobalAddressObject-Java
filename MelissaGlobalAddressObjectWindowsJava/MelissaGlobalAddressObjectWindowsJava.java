import com.melissadata.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Global Address Object corrects, verifies, and enhances Global addresses from over
 * 250+ countries and territories. International address data quality is a challenge for
 * organizations of all sizes. Its differing address structures, terms, and alphabets can
 * have a substantial negative impact on your data-driven initiatives if handled poorly.
 * By using Global Address Object, you'll reduce undeliverables, increase communication
 * efforts, and save money on all your marketing campaigns.
 *
 * <p>High-level flow of this sample:
 * <ol>
 *   <li>SETUP     - create an mdGlobalAddr instance, hand it the license string and the
 *                   path to the data files, then InitializeDataFiles() (one time).</li>
 *   <li>INPUT     - set the address fields via SetInputParameter("input...", ...).</li>
 *   <li>PROCESS   - VerifyAddress() validates and standardizes the address.</li>
 *   <li>READ      - pull the corrected fields back out with GetOutputParameter("...")
 *                   (formattedAddress, latitude, iso2Code, MAK, ...).</li>
 *   <li>INTERPRET - GetOutputParameter("resultCodes") returns comma-separated result
 *                   codes describing what the object did/found.</li>
 * </ol>
 *
 * <p>This object uses the name/value parameter API: inputs are supplied by name through
 * SetInputParameter("input...", value) and every result - including the result codes and
 * the initialization status - is read back by name through GetOutputParameter("..."),
 * rather than the dedicated Get* getters used by the other Melissa objects.
 *
 * <p>The pieces in this file map onto that flow:
 * <ul>
 *   <li>main / RunAsConsole / ParseArguments : console harness (argument parsing + the interactive loop).</li>
 *   <li>GlobalAddressObject                  : thin wrapper around mdGlobalAddr (setup + the call sequence).</li>
 *   <li>DataContainer                        : holds one record's input fields, plus a small request filter.</li>
 * </ul>
 *
 * <p>Where mdGlobalAddr comes from:
 * The mdGlobalAddr and mdGlobalAddrJNI classes in com/melissadata come from mdGlobalAddr_JavaCode.zip,
 * which the accompanying MelissaGlobalAddressObjectWindowsJava.ps1 script downloads and
 * expands into com/melissadata on every run. mdGlobalAddrJNI declares the native methods and
 * loads mdGlobalAddrJavaWrapper.dll, the JNI shim that calls into mdGlobalAddr.dll,
 * which ships alongside mdAddr.dll, mdGeo.dll, and mdRightFielder.dll.
 *
 * <p>Reference:
 * <ul>
 *   <li>Quickstart    : https://docs.melissa.com/on-premise-api/global-address-object/global-address-object-quickstart.html</li>
 *   <li>Release notes : https://releasenotes.melissa.com/on-premise-api/global-address-object/</li>
 *   <li>Result codes  : https://docs.melissa.com/on-premise-api/global-address-object/result-codes.html</li>
 * </ul>
 */
public class MelissaGlobalAddressObjectWindowsJava {

  /**
   * Entry point. Reads the optional command-line arguments, then hands control to
   * RunAsConsole, which performs the actual Global Address Object setup and processing.
   *
   * @param args The raw command-line arguments
   * @throws IOException if reading from standard input fails
   */
  public static void main(String args[]) throws IOException {
    // Populated by ParseArguments below.
    String[] arguments = ParseArguments(args);
    String license = arguments[0];
    String testAddressLine1 = arguments[1];
    String testAddressLine2 = arguments[2];
    String testAddressLine3 = arguments[3];
    String testLocality = arguments[4];
    String testAdministrativeArea = arguments[5];
    String testPostalCode = arguments[6];
    String testCountry = arguments[7];
    String dataPath = arguments[8];

    RunAsConsole(license, testAddressLine1, testAddressLine2, testAddressLine3, testLocality, testAdministrativeArea,
        testPostalCode, testCountry, dataPath);
  }

  /**
   * Reads the supported command-line options and returns them.
   *
   * <p>Recognized flags (each followed by its value):
   * <ul>
   *   <li>--license / -l             : the Melissa license string</li>
   *   <li>--addressLine1 / -a1       : street address line 1</li>
   *   <li>--addressLine2 / -a2       : street address line 2</li>
   *   <li>--addressLine3 / -a3       : street address line 3</li>
   *   <li>--locality / -lo           : locality (city)</li>
   *   <li>--administrativeArea / -aa : administrative area (state/province)</li>
   *   <li>--postalCode / -p          : postal code</li>
   *   <li>--country / -c             : country</li>
   *   <li>--dataPath / -d            : path to the Global Address Object data files</li>
   * </ul>
   *
   * <p>A flag is only consumed when the token after it is not itself a recognized flag,
   * and a flag in the final position is ignored, so a value-less flag cannot swallow the
   * next option.
   *
   * @param args The raw command-line arguments to parse.
   * @return A String array of { license, testAddressLine1, testAddressLine2, testAddressLine3,
   *         testLocality, testAdministrativeArea, testPostalCode, testCountry, dataPath }.
   */
  public static String[] ParseArguments(String[] args) {
    String license = "", testAddressLine1 = "", testAddressLine2 = "", testAddressLine3 = "", testLocality = "",
        testAdministrativeArea = "", testPostalCode = "", testCountry = "", dataPath = "";
    List<String> argumentStrings = Arrays.asList("--license", "-l", "--addressLine1", "-a1", "--addressLine2", "-a2",
        "--addressLine3", "-a3", "--locality", "-lo", "--administrativeArea", "-aa", "--postalCode", "-p", "--country",
        "-c", "--dataPath", "-d");
    for (int i = 0; i < args.length - 1; i++) {
      if ((args[i].equals("--license") || args[i].equals("-l")) && (!argumentStrings.contains(args[i + 1]))) {

        if (args[i + 1] != null) {
          license = args[i + 1];
        }
      }
      if ((args[i].equals("--addressLine1") || args[i].equals("-a1")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          testAddressLine1 = args[i + 1];
        }
      }
      if ((args[i].equals("--addressLine2") || args[i].equals("-a2")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          testAddressLine2 = args[i + 1];
        }
      }
      if ((args[i].equals("--addressLine3") || args[i].equals("-a3")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          testAddressLine3 = args[i + 1];
        }
      }
      if ((args[i].equals("--locality") || args[i].equals("-c")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          testLocality = args[i + 1];
        }
      }
      if ((args[i].equals("--administrativeArea") || args[i].equals("-s"))
          && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          testAdministrativeArea = args[i + 1];
        }
      }
      if ((args[i].equals("--postalCode") || args[i].equals("-z")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          testPostalCode = args[i + 1];
        }
      }
      if ((args[i].equals("--country") || args[i].equals("-z")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          testCountry = args[i + 1];
        }
      }
      if ((args[i].equals("--dataPath") || args[i].equals("-d")) && (!argumentStrings.contains(args[i + 1]))) {
        if (args[i + 1] != null) {
          dataPath = args[i + 1];
        }
      }
    }

    return new String[] { license, testAddressLine1, testAddressLine2, testAddressLine3, testLocality,
        testAdministrativeArea, testPostalCode, testCountry, dataPath };

  }

  /**
   * Sets up the Global Address Object once, then drives the input -> process -> output cycle.
   *
   * <p>In interactive mode (no address args) it loops, prompting for each field until the
   * user answers "N". In one-shot mode (address args supplied) it runs a single pass and exits.
   *
   * @param license                The Melissa license string used to initialize the object.
   * @param testAddressLine1        Street address line 1 for one-shot mode; if empty, the program prompts interactively.
   * @param testAddressLine2        Street address line 2 for one-shot mode.
   * @param testAddressLine3        Street address line 3 for one-shot mode.
   * @param testLocality            Locality (city) for one-shot mode.
   * @param testAdministrativeArea  Administrative area (state/province) for one-shot mode.
   * @param testPostalCode          Postal code for one-shot mode.
   * @param testCountry             Country for one-shot mode.
   * @param dataPath                Path to the Global Address Object data files.
   * @throws IOException if reading from standard input fails
   */
  public static void RunAsConsole(String license, String testAddressLine1, String testAddressLine2,
      String testAddressLine3, String testLocality, String testAdministrativeArea, String testPostalCode,
      String testCountry, String dataPath) throws IOException {
    System.out.println("\n\n========== WELCOME TO MELISSA GLOBAL ADDRESS OBJECT WINDOWS JAVA ==========\n");

    // Construct the wrapper. This is where the object is licensed, pointed at the
    // data files, and initialized (see the GlobalAddressObject constructor below).
    GlobalAddressObject globalAddressObject = new GlobalAddressObject(license, dataPath);
    Boolean shouldContinueRunning = true;

    // Gate the program on a successful initialization. This object reports status via
    // GetOutputParameter; if the data files could not be loaded (bad/expired license,
    // missing or wrong-path data files, ...), "initializeErrorString" holds the reason
    // instead of "No error." and we skip the processing loop entirely.
    if (!globalAddressObject.mdGlobalAddressObj.GetOutputParameter("initializeErrorString").equals("No error."))
      shouldContinueRunning = false;


      
    while (shouldContinueRunning) {
      // Holder for this pass's input and result codes.
      DataContainer dataContainer = new DataContainer();
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));



      if ((testAddressLine1 + testAddressLine2 + testAddressLine3 + testLocality +
          testAdministrativeArea + testPostalCode + testCountry) == null
          || (testAddressLine1 + testAddressLine2 + testAddressLine3 + testLocality +
              testAdministrativeArea + testPostalCode + testCountry).trim().isEmpty()) {
        // Interactive mode: prompt the user for each address field.
        System.out.println("\nFill in each value to see the Global Address Object results");

        System.out.print("     Address Line 1: ");
        dataContainer.AddressLine1 = stdin.readLine();

        System.out.print("     Address Line 2: ");
        dataContainer.AddressLine2 = stdin.readLine();

        System.out.print("     Address Line 3: ");
        dataContainer.AddressLine3 = stdin.readLine();

        System.out.print("           Locality: ");
        dataContainer.Locality = stdin.readLine();

        System.out.print("Administrative Area: ");
        dataContainer.AdministrativeArea = stdin.readLine();

        System.out.print("        Postal Code: ");
        dataContainer.PostalCode = stdin.readLine();

        System.out.print("            Country: ");
        dataContainer.Country = stdin.readLine();

      } else {
        // One-shot mode: use the address fields passed on the command line.
        dataContainer.AddressLine1 = testAddressLine1;
        dataContainer.AddressLine2 = testAddressLine2;
        dataContainer.AddressLine3 = testAddressLine3;
        dataContainer.Locality = testLocality;
        dataContainer.AdministrativeArea = testAdministrativeArea;
        dataContainer.PostalCode = testPostalCode;
        dataContainer.Country = testCountry;
      }

      // Print user input
      System.out.println("\n================================== INPUTS =================================\n");
      System.out.println("               Address Line 1: " + dataContainer.AddressLine1);
      System.out.println("               Address Line 2: " + dataContainer.AddressLine2);
      System.out.println("               Address Line 3: " + dataContainer.AddressLine3);
      System.out.println("                     Locality: " + dataContainer.Locality);
      System.out.println("          Administrative Area: " + dataContainer.AdministrativeArea);
      System.out.println("                  Postal Code: " + dataContainer.PostalCode);
      System.out.println("                      Country: " + dataContainer.Country);

      // Execute Global Address Object
      // Runs the verify sequence; results are then read via GetOutputParameter below.
      globalAddressObject.ExecuteObjectAndResultCodes(dataContainer);

      // Print output
      // Each GetOutputParameter("...") below returns one field the object produced for
      // the most recently processed address. These read directly from the mdGlobalAddr
      // instance, which still holds the results from the Execute call above.
      System.out.println("\n================================== OUTPUT =================================\n");
      System.out.println("\n\tAddress Object Information:");

      System.out.println(
          "\t                          MAK: " + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("MAK"));
      System.out.println("\t                      Company: "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("Organization"));
      System.out.println("\t                     Address1: "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("addressLine1"));
      System.out.println("\t                     Address2: "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("addressLine2"));
      System.out.println("\t                     Address3: "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("addressLine3"));
      System.out.println("\t                     Address4: "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("addressLine4"));
      System.out.println("\t                     Address5: "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("addressLine5"));
      System.out.println(
          "\t                     Locality: " + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("Locality"));
      System.out.println("\t          Administrative Area: "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("AdministrativeArea"));
      System.out.println("\t                  Postal Code: "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("postalCode"));
      System.out.println(
          "\t                      PostBox: " + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("postBox"));
      System.out.println("\t                     Country : "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("countryName"));
      System.out.println(
          "\t                Country ISO 2: " + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("iso2Code"));
      System.out.println(
          "\t                Country ISO 3: " + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("iso3Code"));
      System.out.println(
          "\t                     Latitude: " + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("Latitude"));
      System.out.println(
          "\t                    Longitude: " + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("Longitude"));
      System.out.println("\t            Formatted Address: "
          + globalAddressObject.mdGlobalAddressObj.GetOutputParameter("formattedAddress"));
      System.out.println("\t                 Result Codes: " + dataContainer.ResultCodes);

      // Unlike the other objects, Global Address returns its result codes through
      // GetOutputParameter("resultCodes") (printed above) rather than GetResults(), so
      // the per-code description loop below is left commented out.
      /*
       * String[] rs = dataContainer.ResultCodes.split(",");
       * for (String r : rs)
       *   System.out.println("        " + r + ":"
       *       + globalAddressObject.mdGlobalAddressObj.GetResultCodeDescription(r, mdGlobalAddr.ResultCdDescOpt.ResultCodeDescriptionLong));
       */

      Boolean isValid = false;

      // In one-shot mode there is nothing more to do after a single pass: mark the
      // input handled and stop the outer loop.
      if ((testAddressLine1 + testAddressLine2 + testAddressLine3 + testLocality +
          testAdministrativeArea + testPostalCode + testCountry) != null
          && 
          !(testAddressLine1 + testAddressLine2 + testAddressLine3 + testLocality +
            testAdministrativeArea + testPostalCode + testCountry).trim().isEmpty()) {
        isValid = true;
        shouldContinueRunning = false;
      }

      // Interactive mode: ask whether to process another address. Keep prompting until
      // we get a valid Y/N. "N" ends the program; "Y" falls through to another pass.
      while (!isValid) {
        System.out.println("\nTest another address? (Y/N)");
        String testAnotherResponse = stdin.readLine();

        if (testAnotherResponse != null && !testAnotherResponse.trim().isEmpty()) {
          testAnotherResponse = testAnotherResponse.toLowerCase();
          if (testAnotherResponse.equals("y")) {
            isValid = true;
          } else if (testAnotherResponse.equals("n")) {
            isValid = true;
            shouldContinueRunning = false;
          } else {
            System.out.println("Invalid Response, please respond 'Y' or 'N'");
          }
        }
      }
    }
    System.out.println("\n=================== THANK YOU FOR USING MELISSA JAVA OBJECT ===============\n");

  }
}

/**
 * Wrapper that owns a single Melissa Global Address Object instance and encapsulates the
 * two things every Melissa object needs: one-time setup (license + data files) and the
 * per-record processing sequence. Reuse one instance across many addresses; do NOT
 * re-initialize per address.
 */
class GlobalAddressObject {
  // Path to the Global Address Object data files.
  String dataFilePath;

  // The underlying Melissa Global Address Object instance.
  mdGlobalAddr mdGlobalAddressObj = new mdGlobalAddr();

  /**
   * Performs the mandatory one-time setup, in this required order:
   * <ol>
   *   <li>SetLicenseString         - authorize the object.</li>
   *   <li>SetPathToGlobalAddrFiles - tell it where the data files live.</li>
   *   <li>InitializeDataFiles      - load the data into memory.</li>
   * </ol>
   *
   * @param license  The Melissa license string used to authorize the object.
   * @param dataPath Path to the folder containing the Global Address Object data files.
   */
  public GlobalAddressObject(String license, String dataPath) {
    // Set license string and set path to data files
    mdGlobalAddressObj.SetLicenseString(license);
    dataFilePath = dataPath;

    mdGlobalAddressObj.SetPathToGlobalAddrFiles(dataPath);

    // Load the data files. The returned ProgramStatus reports whether initialization succeeded.
    // If you see a different date than expected, check your license string and either download the new data files
    // or use the Melissa Updater program to update your data files.
    mdGlobalAddr.ProgramStatus pStatus = mdGlobalAddressObj.InitializeDataFiles();

    // If an issue occurred, please investigate the common causes.
    // Common causes: an invalid/expired license, or missing/wrong-path data files.
    if (pStatus != mdGlobalAddr.ProgramStatus.ErrorNone) {
      System.out.println("Failed to Initialize Object.");
      System.out.println(pStatus);
      return;
    }

    // Diagnostic information, handy for confirming the object loaded the data you expect:
    // Build date of the data files
    System.out.println("                DataBase Date: " + mdGlobalAddressObj.GetOutputParameter("databaseDate"));

    // When the license stops working
    System.out
        .println("              Expiration Date: " + mdGlobalAddressObj.GetOutputParameter("databaseExpirationDate"));

    // This number should match with the file properties of the Melissa Object binary file.
    // If TEST appears with the build number, there may be a license key issue.
    System.out.println("               Object Version: " + mdGlobalAddressObj.GetOutputParameter("buildNumber"));
    System.out.println();

  }

  /**
   * Runs the full Global Address Object processing sequence for one address. This is the
   * canonical per-record call pattern to copy into your own application:
   *   ClearProperties -> FilterRequest -> SetInputParameter (per field) -> VerifyAddress
   * Results are read afterwards via GetOutputParameter (see RunAsConsole).
   *
   * @param data The record to process; its address fields are read as input, and ResultCodes
   *             is populated with this run's result codes.
   */
  public void ExecuteObjectAndResultCodes(DataContainer data) {

    // Reset any state left over from a previous address so fields don't bleed across records.
    mdGlobalAddressObj.ClearProperties();

    // Drop any address line that just repeats locality/area/postal (see DataContainer).
    data.FilterRequest();

    // Hand each input field to the object by its parameter name.
    mdGlobalAddressObj.SetInputParameter("inputAddressLine1", data.AddressLine1);
    mdGlobalAddressObj.SetInputParameter("inputAddressLine2", data.AddressLine2);
    mdGlobalAddressObj.SetInputParameter("inputAddressLine3", data.AddressLine3);
    mdGlobalAddressObj.SetInputParameter("inputLocality", data.Locality);
    mdGlobalAddressObj.SetInputParameter("inputAdministrativeArea", data.AdministrativeArea);
    mdGlobalAddressObj.SetInputParameter("inputPostalCode", data.PostalCode);
    mdGlobalAddressObj.SetInputParameter("inputCountry", data.Country);

    // Validate and standardize the address
    mdGlobalAddressObj.VerifyAddress();

    // ResultsCodes explain any issues Global Address Object has with the object.
    // List of result codes for Global Address Object
    // https://docs.melissa.com/on-premise-api/global-address-object/result-codes.html
    data.ResultCodes = mdGlobalAddressObj.GetOutputParameter("resultCodes");

  }
}

/**
 * Holds one record's input address fields, plus a small pre-processing filter
 * (FilterRequest) the wrapper calls before sending the data to the object.
 */
class DataContainer {
  // Input: the first address line to process.
  public String AddressLine1;

  // Input: the second address line to process.
  public String AddressLine2;

  // Input: the third address line to process.
  public String AddressLine3;

  // Input: the locality (city) to process.
  public String Locality;

  // Input: the administrative area (state/province) to process.
  public String AdministrativeArea;

  // Input: the postal code to process.
  public String PostalCode;

  // Input: the country to process.
  public String Country;

  // Output: comma-separated result codes (this sample reads them via GetOutputParameter).
  public String ResultCodes;

  /**
   * Drops an address line that merely repeats the locality / administrative area /
   * postal code (an "area stack"), so those values are not sent to the object twice.
   */
  public void FilterRequest() {
    if (CheckForAreaStack(AddressLine3)) {
      AddressLine3 = "";
    } else if (CheckForAreaStack(AddressLine2)) {
      AddressLine3 = "";
      AddressLine2 = "";
    }
  }

  /**
   * Returns true when the given address line is itself the locality, the administrative
   * area, and the postal code - i.e. it is a redundant "area stack" line. Note this Java
   * sample compares each field for exact equality with the whole line, where the .NET
   * sample tests for containment.
   *
   * @param addressLine The address line to test.
   * @return true if the line is a redundant area stack; false otherwise.
   */
  private boolean CheckForAreaStack(String addressLine) {
    boolean localityCheck = false;
    boolean adminAreaCheck = false;
    boolean postalCheck = false;

    if (Locality.equals(addressLine) && !Locality.equals("")) {
      localityCheck = true;
    }
    if (AdministrativeArea.equals(addressLine) && !AdministrativeArea.equals("")) {
      adminAreaCheck = true;
    }
    if (PostalCode.equals(addressLine) && !PostalCode.equals("")) {
      postalCheck = true;
    }

    return localityCheck && adminAreaCheck && postalCheck;
  }
}
