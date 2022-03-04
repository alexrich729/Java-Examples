package examples;

/**
 *	File utilities for reading and writing, just improves error messages.
 *
 *	@author Alex Richardson
 *	@since August 21, 2019
 */
   
public class FileUtils {
   /** opens a file to read using the Scanner class
	* @param fileName		name of the file to open
	* @return				the java.util.Scanner object reading the file
	* @throws javio.io.FileNotFoundException if the named file cannot be opened for
	*     reading
	*/
	public static java.util.Scanner openToRead(String fileName)	throws java.io.FileNotFoundException {
		java.util.Scanner input = null;
		try {
			input = new java.util.Scanner(new java.io.File(fileName));
		}
		catch (java.io.FileNotFoundException e) {
			throw new java.io.FileNotFoundException("ERROR: Cannot open '" + fileName + "' for reading: " + e.getLocalizedMessage());
		}
		return input;
	}
	
	/**		Opens a file to write using the PrintWriter class
	 * @param fileName		name of the file to open
	 * @return				the java.io.PrintWriter object to the file
	 * @throws javio.io.FileNotFoundException if the named file cannot be opened for
	 *     writing
	 */
	 public static java.io.PrintWriter openToWrite (String fileName) throws java.io.FileNotFoundException {
		java.io.PrintWriter output = null;
		
		try	{
			output = new java.io.PrintWriter(new java.io.File(fileName));
		}
		catch (java.io.FileNotFoundException e)	{
			throw new java.io.FileNotFoundException("ERROR: Cannot open '" + fileName + "' for writing: " + e.getLocalizedMessage());
		}
		return output;
	 }
}