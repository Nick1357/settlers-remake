package jsettlers.common.images;
import java.util.Arrays;

// DO NOT EDIT THIS FILE, IT IS GENERATED

public final class TextureMap {
	private TextureMap() {}

	public static int getIndex(String name) {
		int arrindex = Arrays.binarySearch(names, name);
		return indexes[arrindex];
	}

	private static final String[] names = new String[] {
		"lagerhaus.0",
	};
	private static final int[] indexes = new int[] {
		0,
	};
}
