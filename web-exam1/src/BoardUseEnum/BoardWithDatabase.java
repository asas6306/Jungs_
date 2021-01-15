package BoardUseEnum;

import java.sql.SQLException;
import java.util.Scanner;

public class BoardWithDatabase {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		ArticleDaoV2 ad = new ArticleDaoV2();
		MemberDaoV2 md = new MemberDaoV2();

		while (true) {
			if (MemberDaoV2.m.isBan()) {
				System.out.println("You have banned");
				System.out.println("Press any key ");
				md.setDefaultMember();
			} else {
				System.out.println("============================================");
				System.out.println("1.add 2.list 3.member 4.sort 5.page 6.search 99.makeArticle");
				System.out.println("============================================");
				System.out.print(MemberDaoV2.m.getNickname() + " > ");
				int input = Integer.parseInt(sc.nextLine());

				if (input == 1) {
					ad.add();
				} else if (input == 2) {
					ad.listAll();
					ad.choiceArticle();
				} else if (input == 3) {
					md.memberFlatform();
				} else if (input == 4) {
					ad.sort();
				} else if (input == 5) {
					ad.page();
				} else if (input == 6) {
					ad.search();
				} else if (input == 99) {
					ad.makeCashArticle();
				} else {
					System.out.println("Can't");
				}

			}
		}
	}
}