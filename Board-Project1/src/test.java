
public class test {
	public static void main(String[] args) {
		String email = "asas6306@naver.com";
		String[] emails = email.split("@");
		
		int length = emails[0].length();
		
		String s = emails[0].substring(0, 2);
		
		System.out.println(email);
		System.out.println(emails[0]);
		System.out.println(emails[1]);
		System.out.println(s);
		System.out.println(length);
		
		for(int i = 0; i < emails[0].length()-2; i++) {
			s += "*";
		}
		System.out.println(s+"@"+emails[1]);
		
	}
}
