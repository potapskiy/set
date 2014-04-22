package logic;

import utils.Constants;

public class SetFactory {
	
	public static ISet getSet(int setType, int dataType, int length) {
		ISet set = null;
		
		
		
		if (setType == Constants.LIST_TYPE){
			
			switch (dataType) {
			case Constants.INT_TYPE:
				return new LinkedListSet<Integer>();
				
			case Constants.DOUBLE_TYPE:
				return new LinkedListSet<Double>();
				
			case Constants.STRING_TYPE:
				return new LinkedListSet<String>();
				
			case Constants.CHAR_TYPE:
				return new LinkedListSet<Character>();
				

			default:
				break;
			}
			
		}
		
		return set;
	}
	
	

}
