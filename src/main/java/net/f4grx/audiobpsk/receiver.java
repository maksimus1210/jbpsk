package net.f4grx.audiobpsk;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.swing.DefaultComboBoxModel;
import org.jtransforms.fft.DoubleFFT_1D;

/**
 *
 * @author f4grx
 */
public class receiver extends javax.swing.JFrame implements RecorderCallback {
    
    private Recorder recorder;
    private double imax;
    
    /**
     * Creates new form receiver
     */
    public receiver() {
        imax = 10000;
        
        initComponents();

        DefaultComboBoxModel<MixerProxy> lm = new DefaultComboBoxModel<>();
        cmbDevices.setModel(lm);
        
        Mixer.Info[] ms = AudioSystem.getMixerInfo();
        for(Mixer.Info m: ms) {
            Mixer mx = AudioSystem.getMixer(m);
            System.out.println("mixer : "+mx.getMixerInfo().getName());
            Line.Info[] li;
            int src = 0;
            int tgt = 0;
            li = mx.getSourceLineInfo();
            for(Line.Info l: li) {
                src++;
                try {
                    System.out.println("  source: "+mx.getLine(l).toString());
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(transmitter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            li = mx.getTargetLineInfo();
            for(Line.Info l: li) {
                tgt++;
                try {
                    System.out.println("  target: "+mx.getLine(l).toString());
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(transmitter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(tgt>0) {
                lm.addElement(new MixerProxy(mx));
                System.out.println("    added");
            }
        }

    }

    private void open() {
        recorder=null;
        Line line;
        DefaultComboBoxModel<MixerProxy> mod = (DefaultComboBoxModel<MixerProxy>) cmbDevices.getModel();
        Mixer m = mod.getElementAt(cmbDevices.getSelectedIndex()).getMixer();
        Line.Info[] li = m.getTargetLineInfo();
        Line curline = null;
        for(Line.Info l: li) {
            try {
                line = m.getLine(l);
                System.out.println("  target: "+line.toString());
                if(line instanceof TargetDataLine) {
                    curline = line;
                    recorder = new Recorder((TargetDataLine)curline);
                    recorder.setCallback(this);
                    recorder.start();
                    break;
                }
            } catch (LineUnavailableException ex) {
                Logger.getLogger(transmitter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(curline==null) {
            System.out.println("no target data line found");
        } else {
            System.out.println("tdl found");
        }
    }

    private void close() {
        recorder.stop();
        recorder=null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbDevices = new javax.swing.JComboBox();
        btnOpenClose = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtBauds = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        panWave = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtImax = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSetFmax = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("bpsk receiver");

        jLabel1.setText("Audio in :");

        btnOpenClose.setText("Open");
        btnOpenClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenCloseActionPerformed(evt);
            }
        });

        jLabel4.setText("Baud rate :");

        txtBauds.setText("1200");

        jLabel5.setText("bauds");

        panWave.setBackground(new java.awt.Color(255, 255, 255));
        panWave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panWaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panWaveLayout = new javax.swing.GroupLayout(panWave);
        panWave.setLayout(panWaveLayout);
        panWaveLayout.setHorizontalGroup(
            panWaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panWaveLayout.setVerticalGroup(
            panWaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );

        jLabel6.setText("Amp");

        txtImax.setText("0");

        jLabel7.setText("samples");

        btnSetFmax.setText("Set");
        btnSetFmax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetFmaxActionPerformed(evt);
            }
        });

        jCheckBox1.setText("auto");

        lblStatus.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbDevices, 0, 402, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOpenClose))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBauds)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(4, 4, 4)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtImax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetFmax))
                    .addComponent(panWave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbDevices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenClose))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBauds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtImax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnSetFmax)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panWave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenCloseActionPerformed
        // TODO add your handling code here:
        if(recorder==null) {
            open();
        } else {
            close();
        }

        btnOpenClose.setText(recorder==null?"Open":"Close");
    }//GEN-LAST:event_btnOpenCloseActionPerformed

    private void btnSetFmaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetFmaxActionPerformed
        imax = Double.parseDouble(txtImax.getText());
    }//GEN-LAST:event_btnSetFmaxActionPerformed

    private void panWaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panWaveMouseClicked
        if(recorder==null) {
            return;
        }
            
        int x = evt.getX();
        double freq = x / (double)panWave.getWidth();
        freq *= recorder.getSampleRate() / 2.0;
        
        System.out.println("x -> "+x+" freq -> "+freq);
        recorder.hintFreq(freq);
    }//GEN-LAST:event_panWaveMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpenClose;
    private javax.swing.JButton btnSetFmax;
    private javax.swing.JComboBox cmbDevices;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel panWave;
    private javax.swing.JTextField txtBauds;
    private javax.swing.JTextField txtImax;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onBuffer(double[] buf, double freq) {
        if(buf==null) {
            return;
        }
        Graphics g = panWave.getGraphics();
        
        int w = panWave.getWidth();
        int h = panWave.getHeight();
        int s = buf.length;
        g.clearRect(0, 0, w-1, h-1);

        DoubleFFT_1D fft = new DoubleFFT_1D(buf.length);
        fft.realForward(buf);
        int n = buf.length/2;
        int off = 0;
        double max = 0;
        for(int i=0;i<n;i++) {
            double a = buf[off++];
            double b = buf[off++];
            buf[i] = Math.sqrt(a*a+b*b);
            if(buf[i]>max) {
                max = buf[i];
            }
        }

        for(int i=0;i<n;i++) {
            int x = (i * w) / n;
            int y = (int)(h * (1.0d - buf[i]/max));
            g.drawLine(x, y, x, h-1);
        }
        
        int x = (int) ((w * freq) / (recorder.getSampleRate() / 2.0));
        g.setColor(Color.RED);
        g.drawLine(x, 0, x, h-1);
    }

    @Override
    public void onLock(boolean locked, double error, double tone) {
        lblStatus.setText( (locked?"LOCKED":"unlocked") + "  tone "+tone);
    }
        
}
