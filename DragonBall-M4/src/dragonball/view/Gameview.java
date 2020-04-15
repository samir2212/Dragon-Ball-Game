package dragonball.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import com.sun.media.sound.AlawCodec;

import dragonball.model.game.Game;

public class Gameview extends JFrame {

	private JButton startButton;
	private JButton LoadButton;
    private JTextArea LoadArea;
	public Gameview() {
        startButton = new JButton("Start");
        startButton.setBounds(20,50,200,25);
        startButton.setOpaque(false);
        startButton.setRolloverEnabled(true);
        LoadButton = new JButton("Load");
        LoadButton.setBounds(20,150,100,25);
        LoadButton.setOpaque(false);
        LoadArea = new JTextArea();
        LoadArea.setBounds(120,150,100,25);
        ImagePanel panel = new ImagePanel("start.jpg");
        panel.setLayout(null);
        panel.add(startButton);
        panel.add(LoadButton);
        panel.add(LoadArea);
        add(panel);
		this.setTitle("DragonBall");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(50, 50, 800, 500);
		this.setVisible(true);

	}

	public JTextArea getLoadArea() {
		return LoadArea;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public JButton getLoadButton() {
		return LoadButton;
	}

	public static void main(String[] args) {
		Gameview x = new Gameview();
		x.repaint();
	}


}
