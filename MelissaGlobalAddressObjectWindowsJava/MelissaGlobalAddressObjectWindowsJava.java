import com.melissadata.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MelissaGlobalAddressObjectWindowsJava {

  public static void main(String args[]) throws IOException {
    // Variables
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

  public static void RunAsConsole(String license, String testAddressLine1, String testAddressLine2,
      String testAddressLine3, String testLocality, String testAdministrativeArea, String testPostalCode,
      String testCountry, String dataPath) throws IOException {
    System.out.println("\n\n========== WELCOME TO MELISSA GLOBAL ADDRESS OBJECT WINDOWS JAVA ==========\n");


    GlobalAddressObject globalAddressObject = new GlobalAddressObject(license, dataPath);
    Boolean shouldContinueRunning = true;

    if (!globalAddressObject.mdGlobalAddressObj.GetOutputParameter("initializeErrorString").equals("No error."))
      shouldContinueRunning = false;


      
    while (shouldContinueRunning) {
      DataContainer dataContainer = new DataContainer();
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));



      if ((testAddressLine1 + testAddressLine2 + testAddressLine3 + testLocality +
          testAdministrativeArea + testPostalCode + testCountry) == null
          || (testAddressLine1 + testAddressLine2 + testAddressLine3 + testLocality +
              testAdministrativeArea + testPostalCode + testCountry).trim().isEmpty()) {
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

      // Execute Address Object
      globalAddressObject.ExecuteObjectAndResultCodes(dataContainer);

      // Print output
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

      Boolean isValid = false;
      if ((testAddressLine1 + testAddressLine2 + testAddressLine3 + testLocality +
          testAdministrativeArea + testPostalCode + testCountry) != null
          && 
          !(testAddressLine1 + testAddressLine2 + testAddressLine3 + testLocality +
            testAdministrativeArea + testPostalCode + testCountry).trim().isEmpty()) {
        isValid = true;
        shouldContinueRunning = false;
      }

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

class GlobalAddressObject {
  // Path to Phone Object data files (.dat, etc)
  String dataFilePath;

  // Create instance of Melissa Phone Object
  mdGlobalAddr mdGlobalAddressObj = new mdGlobalAddr();

  public GlobalAddressObject(String license, String dataPath) {
    // Set license string and set path to data files (.dat, etc)
    mdGlobalAddressObj.SetLicenseString(license);
    dataFilePath = dataPath;

    mdGlobalAddressObj.SetPathToGlobalAddrFiles(dataPath);

    // If you see a different date than expected, check your license string and
    // either download the new data files or use the Melissa Updater program to
    // update your data files.

    mdGlobalAddr.ProgramStatus pStatus = mdGlobalAddressObj.InitializeDataFiles();

    if (pStatus != mdGlobalAddr.ProgramStatus.ErrorNone) {
      // Problem during initialization
      System.out.println("Failed to Initialize Object.");
      System.out.println(pStatus);
      return;
    }

    System.out.println("                DataBase Date: " + mdGlobalAddressObj.GetOutputParameter("databaseDate"));
    System.out
        .println("              Expiration Date: " + mdGlobalAddressObj.GetOutputParameter("databaseExpirationDate"));

    /**
     * This number should match with the file properties of the Melissa Object
     * binary file.
     * If TEST appears with the build number, there may be a license key issue.
     */
    System.out.println("               Object Version: " + mdGlobalAddressObj.GetOutputParameter("buildNumber"));
    System.out.println();

  }

  // This will call the lookup function to process the input address as well as
  // generate the result codes
  public void ExecuteObjectAndResultCodes(DataContainer data) {
    mdGlobalAddressObj.ClearProperties();

    data.FilterRequest();

    mdGlobalAddressObj.SetInputParameter("inputAddressLine1", data.AddressLine1);
    mdGlobalAddressObj.SetInputParameter("inputAddressLine2", data.AddressLine2);
    mdGlobalAddressObj.SetInputParameter("inputAddressLine3", data.AddressLine3);
    mdGlobalAddressObj.SetInputParameter("inputLocality", data.Locality);
    mdGlobalAddressObj.SetInputParameter("inputAdministrativeArea", data.AdministrativeArea);
    mdGlobalAddressObj.SetInputParameter("inputPostalCode", data.PostalCode);
    mdGlobalAddressObj.SetInputParameter("inputCountry", data.Country);
    mdGlobalAddressObj.VerifyAddress();
    data.ResultCodes = mdGlobalAddressObj.GetOutputParameter("resultCodes");

    // ResultsCodes explain any issues Address Object has with the object.
    // List of result codes for Address Object
    // https://wiki.melissadata.com/index.php?title=Result_Code_Details#Global_Address_Object

  }
}

class DataContainer {
  public String AddressLine1;
  public String AddressLine2;
  public String AddressLine3;
  public String Locality;
  public String AdministrativeArea;
  public String PostalCode;
  public String Country;
  public String ResultCodes;

  public void FilterRequest() {
    if (CheckForAreaStack(AddressLine3)) {
      AddressLine3 = "";
    } else if (CheckForAreaStack(AddressLine2)) {
      AddressLine3 = "";
      AddressLine2 = "";
    }
  }

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
