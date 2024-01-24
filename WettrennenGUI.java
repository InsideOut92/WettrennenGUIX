import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class WettrennenGUI{
    JFrame frame = new JFrame("Wettrennen");
    JLabel anzahlTeilnehmer = new JLabel("Anzahl der Teilnehmer");
    JTextField teilnehmerTF = new JTextField();
    JLabel anzahlRunden = new JLabel("Anzahl der Runden");
    JTextField rundenTF = new JTextField();
    JButton startButton = new JButton("Starte Rennen");

    JLabel platzierungsLabel = new JLabel("Platzierung");

    JPanel vehiclePanel = new JPanel();
    JPanel treppchen = new JPanel();
    PlatzierungsPanel firstPlace = new PlatzierungsPanel();
    PlatzierungsPanel secondPlace = new PlatzierungsPanel();
    PlatzierungsPanel thirdPlace = new PlatzierungsPanel();

    CheckBoxPanel suv_panel = new CheckBoxPanel("SUV");
    CheckBoxPanel traktor_panel = new CheckBoxPanel("Traktor");
    CheckBoxPanel motorrad_panel = new CheckBoxPanel("Motorrad");
    CheckBoxPanel rennauto_panel = new CheckBoxPanel("Rennauto");

    public WettrennenGUI(){}

    private void createAndShowGUI(){
        JPanel panel = new JPanel();

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(gbl);
        gbl.setConstraints(panel, gbc);

        vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.X_AXIS));
        vehiclePanel.add(suv_panel);
        vehiclePanel.add(traktor_panel);
        vehiclePanel.add(motorrad_panel);
        vehiclePanel.add(rennauto_panel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        // ermöglicht die Überdeckung von 3 Spalten
        gbc.gridwidth = 3;
        panel.add(vehiclePanel, gbc);

        // setzt für alle nachfolgenden Elemente die Spaltenbreite wieder auf 1
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(anzahlRunden, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(rundenTF, gbc);
        rundenTF.setPreferredSize(new Dimension(100, 30));

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(anzahlTeilnehmer, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(teilnehmerTF, gbc);
        teilnehmerTF.setPreferredSize(new Dimension(100, 30));

        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(startButton, gbc);

        treppchen.setLayout(new BoxLayout(treppchen, BoxLayout.Y_AXIS));
        treppchen.add(platzierungsLabel);
        treppchen.add(firstPlace);
        treppchen.add(secondPlace);
        treppchen.add(thirdPlace);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(treppchen, gbc);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public void main(){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                createAndShowGUI();
                setButtonListener();
            }
        });
    }

    private void setButtonListener(){
        ActionListener startListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int rundenZahl = 0;
                try{
                    rundenZahl = Integer.parseInt(rundenTF.getText());
                } catch(Exception exception){
                    rundenZahl = 6;
                }

                int teilnehmerZahl = 0;
                try{
                    teilnehmerZahl = Integer.parseInt(teilnehmerTF.getText());
                } catch(Exception exception){
                    teilnehmerZahl = 10;
                }

                ArrayList<String> vehicleList = new ArrayList<>();
                if(traktor_panel.checkBox.isSelected()){
                    vehicleList.add("Traktor");
                }

                if(suv_panel.checkBox.isSelected()) {
                    vehicleList.add("SUV");
                }

                if(rennauto_panel.checkBox.isSelected()) {
                    vehicleList.add("Rennauto");
                }

                if (motorrad_panel.checkBox.isSelected()) {
                    vehicleList.add("Motorrad");
                }

                if (vehicleList.size() == 0) {
                    Collections.addAll(vehicleList, "Rennauto", "SUV", "Traktor", "Motorrad");
                }

                String[] vehicleArray = vehicleList.toArray(new String[vehicleList.size()]);

                // Wettrennen initialisieren
                Wettrennen wettrennen = new Wettrennen();
                wettrennen.initializeGUI(rundenZahl, teilnehmerZahl, vehicleArray);

                // Wettrennen starten
                wettrennen.raceStart();

                wettrennen.sortWinner();

                ArrayList<Fahrzeug> top3Liste = wettrennen.top3();

                // Platzierung aus dem Rennen lesen
                firstPlace.setPlatzierung("1. Platz");
                firstPlace.setFahrzeug(top3Liste.get(0));

                secondPlace.setPlatzierung("2. Platz");
                secondPlace.setFahrzeug(top3Liste.get(1));

                thirdPlace.setPlatzierung("3. Platz");
                thirdPlace.setFahrzeug(top3Liste.get(2));

                platzierungsLabel.setText("<html>Platzierung: <br/> " +
                        "1.Platz: " + top3Liste.get(0).getKennzeichen() + " : " + top3Liste.get(0).getRennstrecke() + " <br/> " +
                        "2.Platz: " + top3Liste.get(1).getKennzeichen() + " : " + top3Liste.get(1).getRennstrecke()+ " <br/> " +
                        "3.Platz: " + top3Liste.get(2).getKennzeichen() + " : " + top3Liste.get(2).getRennstrecke()+ " </html>");
            }
        };
        startButton.addActionListener(startListener);
    }
}

// ToDone: Fahrzeugklassen über Bilder+Checkbox auswählen für den RandomGenerator
    // ToDone: Bild raussuchen für die Checkboxen
    // ToDone: Bilder in Projekt einfügen
    // ToDone: Bilder in die Checkboxen einbinden
    // ToDone: Bilder in der Größe anpassen
    // ToDone: RandomGenerator modifizieren, dass er nur die ausgewählten Fahrzeugklassen nimmt
    // ToDone: Anpassungen in der Rennerstellung
    // ToDone: Liste aus ausgewählten Bildern erstellen


