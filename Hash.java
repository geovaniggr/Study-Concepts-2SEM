import java.util.Arrays;
import java.util.ArrayList;

public class Hash {

    String [] theArray;
    int arraySize;
    int itemsInArray = 0;

    public Hash( int size ) {
        this.arraySize = size;
        theArray = new String[size];
        Arrays.fill(theArray, "-1" );
    }

    public void hashFunction1( String [] stringsForArray, String [] theArray) {
        
        for(int n = 0; n < stringsForArray.length; n++) {
            String newElementVal = stringsForArray[n];
            theArray[Integer.parseInt(newElementVal)] = newElementVal;
        }
    }

    public void hashFunction2( String [] stringsForArray, String [] theArray) {
        for( int n = 0; n < stringsForArray.length; n++ ) {

            String newElementVal = stringsForArray[n];

            int arrayIndex = Integer.parseInt(newElementVal) % 30;
            System.out.println("Chave Mod= " + arrayIndex + " for value " + newElementVal);

            while( theArray[arrayIndex] != "-1" ) {
                ++arrayIndex;

                System.out.println("Tentativa de Colisão: " + arrayIndex + " ao");
                arrayIndex %= arraySize;
            }

            theArray[arrayIndex] = newElementVal;
        }
    }

    public void displayTable() {
        int increment = 0;

		for (int m = 0; m < 3; m++) {

			increment += 10;

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				System.out.format("| %3s " + " ", n);

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				if (theArray[n].equals("-1"))
					System.out.print("|      ");

				else
					System.out
							.print(String.format("| %3s " + " ", theArray[n]));

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();
        }
    }

    public String findKey( String key ) {
        int arrayIndexHash = Integer.parseInt(key) % 30;

        while( theArray[arrayIndexHash] != "-1") {
            if( theArray[arrayIndexHash] == key) {
                System.out.println(key + "was found in index" + arrayIndexHash);
                return theArray[arrayIndexHash];
            }
            ++arrayIndexHash;
            arrayIndexHash %= arraySize;
        }

        return null;
    }

    public boolean isPrime(int number) {
        
        if(number % 2 == 0) return false;

        for(int i = 3;  i*i <= number; i+=2) {
            
            if(number % i == 0) return false;
        }

        return true;
    }

    public int getNextPrime( int minNumberToCheck) {
        for( int i = minNumberToCheck; true; i++) {
            if(isPrime(i)) return i;
        }
    }

    public void increaseArraySize( int minArraySize ) {
        int newArraySize = getNextPrime(minArraySize);

        moveOldArray(newArraySize);
    }

    public void moveOldArray( int newArraySize) {
        
        String [] cleanArray  = removeEmptySpacesInArray(theArray);
        theArray = new String[newArraySize];
        arraySize = newArraySize;
        fillArrayWithNeg();
        hashFunction2(cleanArray, theArray);
    }

    public String[] removeEmptySpacesInArray( String [] arrayToClean) {

        ArrayList<String> stringList = new ArrayList<String>();

        for( String theString : arrayToClean )
            if(!theString.equals("-1") && !theString.equals(""))
                stringList.add(theString);

        return stringList.toArray(new String[stringList.size()]);
    }

    public void fillArrayWithNeg(){
        Arrays.fill(theArray, "-1");
    } 

    public static void main(String [] args ) {
        Hash theFunc = new Hash(30);
        String[] elementsToAdd = {"1", "5", "17", "21", "26", "1"};
        
		String[] elementsToAdd2 = { 
            "100", "510", "170", "214", "268", "398",
            "235", "802", "900", "723", "699", "1", "16", "999", "890",
            "725", "998", "978", "988", "990", "989", "984", "320", "321",
            "400", "415", "450", "50", "660", "624" 
        };

        String[] elementsToAdd3 = {
        "30", "60", "90", "120", "150", "180",
        "210", "240", "270", "300", "330", "360", "390", "420", "450",
        "480", "510", "540", "570", "600", "989", "984", "320", "321",
        "400", "415", "450", "50", "660", "624" 
        };


        theFunc.hashFunction2(elementsToAdd3, theFunc.theArray);

        theFunc.increaseArraySize(60);
        theFunc.displayTable();
    }
}
