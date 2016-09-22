import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Eliza {

	public Set<String> hedgeSet;

	public Set<String> salutationSet;

	public Set<String> qualifierSet;

	public Map<String, String> replacementMap;

	public Data data;

	public Eliza() {
		data = new Data();

		hedgeSet = data.getHedgeSet();
		salutationSet = data.getSalutationSet();
		qualifierSet = data.getQualifierSet();
		replacementMap = data.getReplacementMap();
	}

	String response = "";

	public void start() {
		System.out.println("Good day. What is your problem?");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter your response here or Q to quit:");
			if (sc.hasNextLine())
				response = sc.nextLine();
			if (response.equalsIgnoreCase("q")) {
				break;
			}
			if (isqualifier(response)) {
				System.out.println(hasSalutation() + randomSet(qualifierSet) + "" + replace(response));
			} else
				System.out.println(hasSalutation() + randomSet(hedgeSet));
		}
		sc.close();
		System.out.println("Have a nice day!");
	}

	public String hasSalutation() {
		String arr[] = response.split(" ");
		String ans = "";
		String salutation = "";
		for (int i = 0; i < arr.length; i++) {
			for (String k : salutationSet) {
				if (arr[i].equalsIgnoreCase(k)) {
					arr[i] = "";
					salutation = randomSet(salutationSet) + "! ";
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i].equalsIgnoreCase(""))
				ans = ans + " " + arr[i];
		}
		response = ans;
		return salutation;
	}

	public String replace(String response) {
		String arr[] = response.split(" ");
		String ans = "";
		for (int i = 0; i < arr.length; i++) {
			for (String k : replacementMap.keySet()) {
				if (arr[i].equalsIgnoreCase(k)) {
					arr[i] = replacementMap.get(k);
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			ans = ans + " " + arr[i];
		}
		return ans;
	}

	private boolean isqualifier(String response) {
		String arr[] = response.split(" ");
		for (String s : arr) {
			for (String d : replacementMap.keySet()) {
				if (s.equalsIgnoreCase(d))
					return true;
			}
		}
		// cdp cod plus
		return false;
	}

	public String randomSet(Set<String> set) {
		int index = new Random().nextInt(set.size());
		Iterator<String> iter = set.iterator();
		for (int i = 0; i < index; i++) {
			iter.next();
		}
		return iter.next();
	}
}
