package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SwingObserverExample {
	JFrame frame;
	Random random = new Random();
	
	public static void main(String[] args) {
		// EDT에서 실행되도록 설정
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SwingObserverExample example = new SwingObserverExample();
				example.go();
			}
		});
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton button = new JButton("할까? 말까?");
		button.addActionListener(new RandomListener());
		
		// 버튼을 프레임에 추가
		frame.getContentPane().add(button);
		frame.setSize(300, 200); // 프레임 크기 설정
		frame.setVisible(true);  // 프레임을 화면에 표시
	}

	class RandomListener implements ActionListener {
		AngelListener angelListener = new AngelListener();
		DevilListener devilListener = new DevilListener();
		
		public void actionPerformed(ActionEvent event) {
			if (random.nextBoolean()) {
				angelListener.actionPerformed(event);
			} else {
				devilListener.actionPerformed(event);
			}
		}
	}

	class AngelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("하지마! 아마 후회할 걸?");
		}
	}

	class DevilListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("그냥 저질러버려~");
		}
	}
}