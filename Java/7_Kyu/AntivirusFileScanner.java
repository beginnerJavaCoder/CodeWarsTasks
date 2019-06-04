/*
Original task - https://www.codewars.com/kata/anti-virus-file-scanner

Task
You are working for NoVirus Security Solutions and they ask you to make a scanner that scans a file inputted by the 
user with the function `scanFile(File,VirusDB)` that takes a File and a VirusDB object 
and return whether a file is safe or not. 
Remember: the searches need to be non-Case-Sensitive
Your class also has the function `setScanIntensity(int)` which changes the scan intensity. 
This will only receive values 0, 1, 2 or 3. This has been done for you.
The scan intensity determines the arrays from the database that will be used. i.e.:
scanIntensity 0 means off(every file is considered safe)
scanIntensity 1 means that only the array intensity1Signatures will be used
scanIntensity 2 means that the arrays intensity1Signatures and intensity2Signatures will be used
scanIntensity 3 means that all 3 arrays will be used

Outputs
The outputs should be: ```"Filename is safe"``` or 
```"Filename is not safe"``` (Filename is the name of the file that you can get with `file.getName()` )

File Class

class File{
  private String name;
  private String data;

  public File(String name,String data){
    this.name = name;
    this.data = data;
  }

  //used in output
  public String getName(){
    return this.name;
  }

  //the String that you need to scan.
  public String getData(){
    return this.data;
  }
}

VirusDB Class

class VirusDB{
   private String[] intensity1Signatures;
   private String[] intensity2Signatures;
   private String[] intensity3Signatures;

   public VirusDB(String[] intensity1Signatures,String[] intensity2Signatures,String[] intensity3Signatures){
     this.intensity1Signatures = intensity1Signatures;
     this.intensity2Signatures = intensity2Signatures;
     this.intensity3Signatures = intensity3Signatures;
   }

   public String[] getSignatures(int arrayNum){
     switch (arrayNum){
       case 1:return this.intensity1Signatures;
       case 2:return this.intensity2Signatures;
       case 3:return this.intensity3Signatures;
       default:return new String[0];
     }
   }
}

Examples

      String[] intensity1signatures = new String[]{
        "malware",
        "virus",
        "infect"
      };

      String[] intensity2signatures = new String[]{
        "ransomware",
        "trojan",
        "trojanHorse",
        "worm",
        "spyware",
        "keystrokelogger",
        "adware",
        "botnet",
        "rootkit",
      };

      String[] intensity3signatures = new String[]{
        "DeleteSys32",
        "OverideMBR",
        "EncryptAll",
        "openrandomwebsite",
        "openrandwebsite",
        "sendalldata",
        "recordKeyboard",
        "recordmouse",
        "destroy",
        "overheat",
        "getfullcontrol",
        "uploadharddrive",
        "uploadharddisk",
        "overload",
        "changeOS",
        "encrypt",
        "changeDesktop",
        "ddos",
        "dos",
        "hide",
        "inject",
        "ransom",
        "getcreditcardinfo",
        "getpasswords",
        "getpass",
      };
      
file name	file data	            scan intensity    result	          comments
file0	    dsadxzdcVirusdsadfads	0	              "file0 is safe"	  scanIntensity 0 means off
file1	    dsadxzdcViRUsdsadfads	1	              "file1 is not safe" make sure that the scan is not case sensitive
Virus	    dsadxz2346dsadfads	    3	              "Virus is safe"	  Name doesn't matter
file4	    gasfdsfwormhmilasd	    1	              "file4 is safe"	  worm is in array 2 while the intensity was 1, so 2 shall not be searched
file5	    ascgEtCReditcaRdiNFolds	3	              "file5 is not safe" getcreditcardinfo is int array 3
 */
 
public class AntiVirus {

    private int scanIntensity = 0;
    
    public void setScanIntensity(int level) {
        scanIntensity = level;
    }
  
    public String scanFile(File file, VirusDB database) {
        String data = file.getData().toLowerCase();
        switch (scanIntensity) {
            case 3 : {
                for (String virus : database.getSignatures(3)) {
                    if(data.contains(virus)) return file.getName() + " is not safe";
                }
            }
            case 2 : {
                for (String virus : database.getSignatures(2)) {
                    if(data.contains(virus)) return file.getName() + " is not safe";
                }
            }
            case 1 : {
                for (String virus : database.getSignatures(1)) {
                    if(data.contains(virus)) return file.getName() + " is not safe";
                }
            }
            default : return file.getName() + " is safe";
        }
    }
}