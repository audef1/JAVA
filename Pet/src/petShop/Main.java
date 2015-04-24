package petShop;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Pet p = null;
		while (p == null) {
			try {
				String name = JOptionPane.showInputDialog(null, "Name");
				p = new Cat(name);
				System.out.println("Name: " + p.getName());
			} catch (Exception e) {
				System.out.println("Gib dem Tier einen Namen!");
				JOptionPane
						.showMessageDialog(null, "Gib dem Tier einen Namen.");
			}
		}
		boolean hasEaten = false;
		while (!hasEaten) {
			Food f = null;
			int feedCheese = JOptionPane.showConfirmDialog(null,
					"Soll " + p.getName() + " Käse essen?", "Fütterung",
					JOptionPane.YES_NO_OPTION);

			if (feedCheese == JOptionPane.YES_OPTION)
				f = new Cheese();
			try {
				p.feed(f);
				hasEaten = true;
			} catch (WrongFoodEx e) {
				System.out.println("Teller ist leer!");
			} catch (SickEx e) {
				System.out.println(p.getName() + " ist ganz fest krank.");
				int heal = JOptionPane.showConfirmDialog(null,
						"Soll " + p.getName() + " geheilt werden?", "Apotheke",
						JOptionPane.YES_NO_OPTION);
				if (heal == JOptionPane.YES_OPTION)
					p.heal();
			}

		}
	}
}