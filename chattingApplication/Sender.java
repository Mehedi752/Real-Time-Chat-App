package chattingApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Sender implements ActionListener {

    JTextField textField; // Text field for typing message
    JPanel chatPanel; // Panel for chatting
    static Box vertical = Box.createVerticalBox(); // Creating a vertical box layout
    static JFrame frame = new JFrame();
    static DataOutputStream dataOutputStream;

    Sender() {

        frame.setLayout(null); // Setting the layout of frame to null

        // -------------Panel for Heading-------------\\
        JPanel headPanel = new JPanel(); // Panel for heading
        headPanel.setBounds(0, 0, 450, 70); // Setting the size of panel
        headPanel.setBackground(new Color(7, 94, 84)); // Setting the background color of panel
        headPanel.setLayout(null); // Setting the layout of panel to null
        frame.add(headPanel); // Adding panel to the frame

        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("src/3.png"));
        Image image2 = image1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel back = new JLabel(image3); // Label for back button
        back.setBounds(5, 20, 25, 25); // Setting the size of label
        headPanel.add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ae) {
                System.exit(0); // Closing the frame
            }
        });

        ImageIcon image4 = new ImageIcon(ClassLoader.getSystemResource("src/myProfile.png"));
        Image image5 = image4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon image6 = new ImageIcon(image5);
        JLabel senderImage = new JLabel(image6);
        senderImage.setBounds(40, 10, 50, 50); // Setting the size of label
        headPanel.add(senderImage);

        ImageIcon image7 = new ImageIcon(ClassLoader.getSystemResource("src/video.png"));
        Image image8 = image7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image9 = new ImageIcon(image8);
        JLabel video = new JLabel(image9);
        video.setBounds(300, 20, 30, 30); // Setting the size of label
        headPanel.add(video);

        ImageIcon image10 = new ImageIcon(ClassLoader.getSystemResource("src/phone.png"));
        Image image11 = image10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon image12 = new ImageIcon(image11);
        JLabel phone = new JLabel(image12);
        phone.setBounds(350, 20, 35, 30); // Setting the size of label
        headPanel.add(phone);

        ImageIcon image13 = new ImageIcon(ClassLoader.getSystemResource("src/3icon.png"));
        Image image14 = image13.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon image15 = new ImageIcon(image14);
        JLabel threeIcon = new JLabel(image15);
        threeIcon.setBounds(400, 20, 13, 25); // Setting the size of label
        headPanel.add(threeIcon);

        JLabel senderName = new JLabel("Mehedi"); // Label for sender name
        senderName.setForeground(Color.WHITE); // Setting the foreground color of label
        senderName.setBounds(110, 15, 100, 20); // Setting the size of label
        senderName.setFont(new Font(("SAN_SERIF"), Font.BOLD, 18)); // Setting the font of label
        headPanel.add(senderName); // Adding label to the panel

        JLabel senderStatus = new JLabel("Active Now"); // Label for sender status
        senderStatus.setForeground(Color.WHITE); // Setting the foreground color of label
        senderStatus.setBounds(110, 30, 100, 20); // Setting the size of label
        senderStatus.setFont(new Font(("SAN_SERIF"), Font.PLAIN, 10)); // Setting the font of label
        headPanel.add(senderStatus); // Adding label to the panel
        // -----------------------------------------------\\

        // -------------Panel for Chatting-------------\\
        chatPanel = new JPanel(); // Panel for chatting
        chatPanel.setBounds(5, 75, 440, 530); // Setting the size of panel
        // chatPanel.setBackground(new Color(204, 255, 229)); // Setting the background
        // color of panel
        frame.add(chatPanel); // Adding panel to the frame

        // -----------------------------------------------\\
        // -------------Panel for Text Field-------------\\
        textField = new JTextField(); // Text field for typing message
        textField.setBounds(5, 615, 310, 30); // Setting the size of text field
        textField.setFont(new Font(("SAN_SERIF"), Font.PLAIN, 12)); // Setting the font of text field
        frame.add(textField); // Adding text field to the frame

        JButton sendButton = new JButton("Send"); // Button for sending message
        sendButton.setBounds(320, 615, 123, 30); // Setting the size of button
        sendButton.setBackground(new Color(7, 94, 84)); // Setting the background color of button
        sendButton.setForeground(Color.WHITE); // Setting the foreground color of button
        sendButton.setFont(new Font(("SAN_SERIF"), Font.PLAIN, 12)); // Setting the font of button
        sendButton.addActionListener(this); // Adding action listener to the button
        frame.add(sendButton); // Adding button to the frame
        // -----------------------------------------------\\

        frame.setSize(450, 650); // Setting the size of frame
        frame.setLocation(100, 20); // Setting the location of frame on the screen
        frame.setUndecorated(true); // Hiding the title bar of frame
        frame.getContentPane().setBackground(Color.WHITE); // Setting the background color of frame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting the default close operation of frame
        frame.setVisible(true); // Setting the visibility of frame to true
    }

    // Overriding the actionPerformed method of ActionListener interface
    @Override
    public void actionPerformed(ActionEvent ae) {
        String message = textField.getText(); // Getting the text from text field
        JPanel messagePanel = formatLabel(message); // Formatting the message

        chatPanel.setLayout(new BorderLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(messagePanel, BorderLayout.LINE_END);
        vertical.add(rightPanel);
        vertical.add(Box.createVerticalStrut(15));

        try {
            dataOutputStream.writeUTF(message); // Writing the message to the data output stream
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        chatPanel.add(vertical, BorderLayout.PAGE_START);
        textField.setText(""); // Setting the text of text field to empty

        frame.repaint(); // Repainting or reloading the frame
        frame.invalidate(); // Invalidating the frame
        frame.validate(); // Validating the frame

    }

    public static JPanel formatLabel(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel chatMessage = new JLabel("<html><p style = \"width : 150px\">" + text + "</p></html>");
        chatMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        chatMessage.setBackground(new Color(37, 211, 102));
        chatMessage.setOpaque(true);
        chatMessage.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(chatMessage);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(simpleDateFormat.format(calendar.getTime()));
        panel.add(time);

        return panel;
    }

    public static JPanel formatLabel2(String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel chatMessage = new JLabel("<html><p style = \"width : 150px\">" + text + "</p></html>");
        chatMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        chatMessage.setBackground(new Color(51, 255, 255));
        chatMessage.setOpaque(true);
        chatMessage.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(chatMessage);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(simpleDateFormat.format(calendar.getTime()));
        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new Sender();

        try {
            ServerSocket serverSocket = new ServerSocket(6001); // Creating a server socket

            while (true) {
                Socket socket = serverSocket.accept(); // Accepting the connection from client
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream()); // Creating a data input
                dataOutputStream = new DataOutputStream(socket.getOutputStream()); // Creating a data output stream

                while (true) {
                    String message = dataInputStream.readUTF();
                    JPanel messagePanel = formatLabel2(message);

                    JPanel leftPanel = new JPanel(new BorderLayout());
                    leftPanel.add(messagePanel, BorderLayout.LINE_START);
                    vertical.add(leftPanel);
                    vertical.add(Box.createVerticalStrut(15));

                    frame.validate();
                    frame.repaint();
                    frame.invalidate();


                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
