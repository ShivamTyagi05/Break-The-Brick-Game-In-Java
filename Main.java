import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
		JFrame obj= new JFrame();
		Play py = new Play();
		obj.setBounds(10,10,700,600);
		obj.setTitle("Break Bricks");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(py);

	}

}
