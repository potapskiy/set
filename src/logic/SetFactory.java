package logic;

import utils.Constants;

public class SetFactory {

	public static ISet getSet(int setType, int dataType, int length) {
		ISet set = null;

		if (setType == Constants.LIST_TYPE) {

			switch (dataType) {
			case Constants.INT_TYPE:
				set = new LinkedListSet<Integer>(dataType);
				break;

			case Constants.DOUBLE_TYPE:
				set = new LinkedListSet<Double>(dataType);
				break;

			case Constants.STRING_TYPE:
				set = new LinkedListSet<String>(dataType);
				break;

			case Constants.CHAR_TYPE:
				set = new LinkedListSet<Character>(dataType);
				break;

			default:
				break;
			}

		}

		return set;
	}

}
