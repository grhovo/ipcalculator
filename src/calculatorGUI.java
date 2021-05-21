import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculatorGUI {
    private javax.swing.JPanel JPanel;
    private JTextField ipaddr_1;
    private JTextField ipaddr_2;
    private JTextField ipaddr_3;
    private JTextField ipaddr_4;
    private JTextField Mask;
    private JTextArea mask_1;
    private JTextArea mask_2;
    private JTextArea mask_3;
    private JTextArea mask_4;
    private JTextArea network;
    private JTextArea info;
    private JButton calculate;
    private JButton delete;
    private JTextArea first_ip;
    private JTextArea last_ip;
    private JTextArea broadcast;
    private JTextArea ipclass;

    public calculatorGUI() {
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ip address definition in array
                int[] ipaddressoctets = new int[4];
                // mask in integer
                int mask = 0;
                // define variables
                boolean EverithingOk=false;
                while(!EverithingOk) {
                    ipaddressoctets[0] = Integer.parseInt(ipaddr_1.getText());
                    ipaddressoctets[1] = Integer.parseInt(ipaddr_2.getText());
                    ipaddressoctets[2] = Integer.parseInt(ipaddr_3.getText());
                    ipaddressoctets[3] = Integer.parseInt(ipaddr_4.getText());
                    for (int i = 0; i < 4; i++) {
                        if (ipaddressoctets[i] > 255 || ipaddressoctets[i] < 0) {
                            JOptionPane.showMessageDialog(null, "Please give correct number for octet","Output",JOptionPane.PLAIN_MESSAGE);
                            ipaddr_1.setText("");
                            ipaddr_2.setText("");
                            ipaddr_3.setText("");
                            ipaddr_4.setText("");
                            break;
                        }
                        EverithingOk=true;
                    }
                }
                EverithingOk=false;
                while (!EverithingOk) {
                    mask = Integer.parseInt(Mask.getText());
                    if (mask > 32 || mask < 0) {
                        JOptionPane.showMessageDialog(null, "Please give correct number for subnet mask","Output",JOptionPane.PLAIN_MESSAGE);
                        Mask.setText("");
                    }
                    else EverithingOk=true;
                }
                //Subnet calculator class definition
                subnetCalculator calculator = new subnetCalculator(ipaddressoctets,mask);
                //Integer mask to x.x.x.x form, where x is integer
                int[] subnetOctet = calculator.convertMaskToOctets();
                mask_1.setText(String.valueOf(subnetOctet[0]));
                mask_2.setText(String.valueOf(subnetOctet[1]));
                mask_3.setText(String.valueOf(subnetOctet[2]));
                mask_4.setText(String.valueOf(subnetOctet[3]));
                //Class definition
                ipClass classNetwork = calculator.classDefinition();
                ipclass.setText(classNetwork.getClassWord());
                info.setText(classNetwork.print());
                int[] networkIP = calculator.networkIP(subnetOctet);
                String networkStr = "";
                for (int i = 0; i < 4; i++) {
                    networkStr += networkIP[i] + ".";
                }
                network.setText(networkStr);
                String firstIPStr = "";
                int[] firstIP = calculator.firstIP(networkIP);
                for (int i = 0; i < 4; i++) {
                    firstIPStr += firstIP[i] + ".";
                }
                first_ip.setText(firstIPStr);
                String broadcastIPStr = "";
                int[] broadcastIP = calculator.broadcastIP(networkIP);
                for (int i = 0; i < 4; i++) {
                    broadcastIPStr += broadcastIP[i] + ".";
                }
                broadcast.setText(broadcastIPStr);
                String lastIPStr = "";
                int[] lastIP = calculator.lastIP(broadcastIP);
                for (int i = 0; i < 4; i++) {
                    lastIPStr += lastIP[i] + ".";
                }
                last_ip.setText(lastIPStr);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipaddr_1.setText("");
                ipaddr_2.setText("");
                ipaddr_3.setText("");
                ipaddr_4.setText("");
                Mask.setText("");
                mask_1.setText("");
                mask_2.setText("");
                mask_3.setText("");
                mask_4.setText("");
                info.setText("");
                network.setText("");
                first_ip.setText("");
                last_ip.setText("");
                broadcast.setText("");
                ipclass.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new calculatorGUI().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
