package Program;

import Model.AssociatesPool;

public class Main {

	public static void main(String[] args) {

		AssociatesPool pool = new AssociatesPool();
		
		pool.addAssociate("member", "Auderset", "Florian");
		pool.addAssociate("backer", "Meier","Hans");
		pool.addAssociate("revisor", "Käser","Stefan");
		pool.listPool();
	}

}
