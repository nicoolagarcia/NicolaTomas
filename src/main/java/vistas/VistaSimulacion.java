/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.SwingUtilities;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import micelaneos.*;
import modelo.CPU;
import modelo.Planificador;
import modelo.Reloj;
/**
 *
 * @author tomas
 */
public class VistaSimulacion extends javax.swing.JFrame {
    DefaultPieDataset dataset1;
    DefaultPieDataset dataset2;
    DefaultPieDataset dataset3;
    DefaultPieDataset dataset4;
    CPU[] cpus;
    Reloj reloj;
    List<Proceso> listolista;
    List<Proceso> todos;
    /**
     * Creates new form VistaSimulacion
     */
    public VistaSimulacion(int tiempo,int politica, List listo,List todos) {
        initComponents();
        dataset1 = new DefaultPieDataset();
        dataset2 = new DefaultPieDataset();
        dataset3 = new DefaultPieDataset();
        dataset4 = new DefaultPieDataset();
        jPanel5.setLayout(new BorderLayout());
        jPanel6.setLayout(new BorderLayout());
        jPanel7.setLayout(new BorderLayout());
        jPanel8.setLayout(new BorderLayout());
        jPanel5.add(createPieChart(dataset2,"CPU 1"), BorderLayout.CENTER);
        jPanel6.add(createPieChart(dataset1,"CPU 2"), BorderLayout.CENTER);
        jPanel7.add(createPieChart(dataset3,"CPU 3"), BorderLayout.CENTER);
        jPanel8.add(createPieChart(dataset4,"Total"), BorderLayout.CENTER);
        this.politica.setSelectedIndex(politica);
        this.tiempoinstruccion.setValue(tiempo);
        this.jLabel16.setText(tiempo + " ms");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.listolista =listo;
        this.todos = todos;
        this.uPcbs();
    }

    public CPU[] getCpus() {
        return cpus;
    }

    public void setCpus(CPU[] cpus) {
        this.cpus = cpus;
    }

    public Reloj getReloj() {
        return reloj;
    }

    public void setReloj(Reloj reloj) {
        this.reloj = reloj;
    }
    
    public VistaSimulacion(){
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    private boolean validateInputs() {
        try {
            // Check if text fields are empty
            if (nombre.getText().isEmpty() || duracion.getText().isEmpty() || cicloexcep.getText().isEmpty() || duracionexcep.getText().isEmpty()) {
                return false;
            }
            // Check if the values are numbers
            Integer.parseInt(duracion.getText()); // duracion
            if(this.tipoproceso.getSelectedIndex()==1){
                Integer.parseInt(cicloexcep.getText()); // cicloexcep
                Integer.parseInt(duracionexcep.getText()); // duracionexcep
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public void setCPU1(String t){
       this.cpu1.setText(t);
    }
    public void uPcbs(){
        String d = "";
        Nodo p = todos.getHead();
        while(p != null){
            d += Planificador.stringInterfaz((Proceso) p.getValue());
            p = p.getpNext();
        }
        this.setPcbs(d);
    }

    public void setCPU2(String t) {
        this.cpu2.setText(t);
    }

    public void setCPU3(String t) {
        this.cpu3.setText(t);
    }
    public void setReloj(String t){
        this.relojglobal.setText(t);
    }

    public void setListos(String t) {
        this.listos.setText(t);
    }

    public void setBloqueados(String t) {
        this.bloqueados.setText(t);
    }

    public void setSalida(String t) {
        this.salida.setText(t);
    }
    public void setPcbs(String t){
        this.pcbs.setText(t);
    }
    public void setTiempoInstruccion(String i){
        this.relojglobal.setText(i);
    }
    public int getTiempoInstrucion(){
        return this.tiempoinstruccion.getValue();
    }
    public int getPolitica(){
        return this.politica.getSelectedIndex();
    }
    private ChartPanel createPieChart(DefaultPieDataset dataset,String title) {
        
        dataset.setValue("Usuario", 0);
        dataset.setValue("Sistema Operativo", 0);

        JFreeChart pieChart = ChartFactory.createPieChart(
                title,
                dataset,
                true, true, false);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("Usuario", Color.BLUE);
        plot.setSectionPaint("Sistema Operativo", Color.RED);
        
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(670, 380));
        return chartPanel;
    }
    public void updateDataset(int chartNumber, String category, int value) {
        SwingUtilities.invokeLater(() -> {
            DefaultPieDataset dataset;
            switch (chartNumber) {
                case 1 -> dataset = dataset1;
                case 2 -> dataset = dataset2;
                case 3 -> dataset = dataset3;
                case 4 -> dataset = dataset4;
                default -> throw new IllegalArgumentException("Invalid chart number: " + chartNumber);
            }

            Number existingValue = dataset.getValue(category);
            int newValue = existingValue.intValue() + value;
            dataset.setValue(category, newValue);

            switch (chartNumber) {
                case 1 -> ((ChartPanel) jPanel5.getComponent(0)).repaint();
                case 2 -> ((ChartPanel) jPanel6.getComponent(0)).repaint();
                case 3 -> ((ChartPanel) jPanel7.getComponent(0)).repaint();
                case 4 -> ((ChartPanel) jPanel8.getComponent(0)).repaint();
            }
        });
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        guardarproceso = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        duracion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cicloexcep = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        duracionexcep = new javax.swing.JTextField();
        tipoproceso = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cpu3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        cpu1 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        cpu2 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        salida = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        pcbs = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        bloqueados = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        listos = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        relojglobal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tiempoinstruccion = new javax.swing.JSlider();
        unidadescpu = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        politica = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        guardarproceso.setText("Añadir");
        guardarproceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarprocesoActionPerformed(evt);
            }
        });
        jPanel2.add(guardarproceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 140, 30));

        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 280, -1));

        jLabel9.setText("Nombre:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLabel10.setText("Duración: ");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        duracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duracionActionPerformed(evt);
            }
        });
        jPanel2.add(duracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 280, -1));

        jLabel11.setText("Cada excepción:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, -1));

        cicloexcep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cicloexcepActionPerformed(evt);
            }
        });
        jPanel2.add(cicloexcep, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 280, -1));

        jLabel12.setText("Duración excepción:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, -1));

        duracionexcep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duracionexcepActionPerformed(evt);
            }
        });
        jPanel2.add(duracionexcep, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 280, -1));

        tipoproceso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPU Bound", "I/O Bound" }));
        tipoproceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoprocesoActionPerformed(evt);
            }
        });
        jPanel2.add(tipoproceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 280, -1));

        jLabel13.setText("Tipo:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 30, 20));

        jTabbedPane1.addTab("Añadir", jPanel2);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("CPU 1", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("CPU 2", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("CPU 3", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Total", jPanel8);

        jPanel1.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 380));

        jTabbedPane1.addTab("Estadisticas", jPanel1);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        cpu3.setEditable(false);
        cpu3.setColumns(20);
        cpu3.setRows(5);
        jScrollPane3.setViewportView(cpu3);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 90, 90));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        cpu1.setEditable(false);
        cpu1.setColumns(20);
        cpu1.setRows(5);
        jScrollPane4.setViewportView(cpu1);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 90, 90));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        cpu2.setEditable(false);
        cpu2.setColumns(20);
        cpu2.setRows(5);
        jScrollPane5.setViewportView(cpu2);

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 90, 90));

        salida.setEditable(false);
        salida.setColumns(20);
        salida.setRows(5);
        jScrollPane1.setViewportView(salida);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 110, 120));

        pcbs.setEditable(false);
        pcbs.setColumns(20);
        pcbs.setRows(5);
        pcbs.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                pcbsCaretUpdate(evt);
            }
        });
        jScrollPane2.setViewportView(pcbs);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 110, 290));

        bloqueados.setEditable(false);
        bloqueados.setColumns(20);
        bloqueados.setRows(5);
        jScrollPane7.setViewportView(bloqueados);

        jPanel4.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 110, 130));

        listos.setEditable(false);
        listos.setColumns(20);
        listos.setRows(5);
        jScrollPane6.setViewportView(listos);

        jPanel4.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 110, 290));

        jLabel1.setText("PCB");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jLabel2.setText("Salida");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, -1, -1));

        jLabel3.setText("Bloqueados");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, -1));

        jLabel4.setText("Listos");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, -1));

        jLabel5.setText("CPU 3");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        jLabel6.setText("Reloj Global");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jLabel7.setText("CPU 2");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        relojglobal.setEditable(false);
        relojglobal.setText("0");
        jPanel4.add(relojglobal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 70, -1));

        jLabel8.setText("CPU 1");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jTabbedPane1.addTab("Simulación", jPanel4);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tiempoinstruccion.setMaximum(5000);
        tiempoinstruccion.setMinimum(1);
        tiempoinstruccion.setValue(5000);
        tiempoinstruccion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tiempoinstruccionStateChanged(evt);
            }
        });
        jPanel3.add(tiempoinstruccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 220, -1));

        unidadescpu.setText("1");
        unidadescpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unidadescpuActionPerformed(evt);
            }
        });
        jPanel3.add(unidadescpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 210, -1));

        jLabel14.setText("CPUs:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, -1));

        jLabel15.setText("Tiempo instrucción: ");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jLabel16.setText("5000 ms");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 110, -1));

        politica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FIFO", "RR", "SPN", "SRT", "HRRN" }));
        politica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                politicaActionPerformed(evt);
            }
        });
        jPanel3.add(politica, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 210, -1));

        jLabel17.setText("Politica de Planificación: ");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        jButton2.setText("Iniciar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 150, 30));

        jTabbedPane1.addTab("Configuración", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void duracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_duracionActionPerformed

    private void cicloexcepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cicloexcepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cicloexcepActionPerformed

    private void duracionexcepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duracionexcepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_duracionexcepActionPerformed

    private void unidadescpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unidadescpuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unidadescpuActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
            int unidades = Integer.parseInt(this.unidadescpu.getText());
            if(unidades >=4 || unidades <= 0){
                int hola = 8/0; 
            }
            for (int i = 0; i < unidades; i++) {
                cpus[i].start();
            }
            reloj.start();
            ProcesoJsonHandler.writeProcesosToJson(todos, "procesos.json");
            this.jButton2.setEnabled(false);
            this.guardarproceso.setEnabled(false);
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void guardarprocesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarprocesoActionPerformed
        // TODO add your handling code here:
        int ciclo = 1; // cicloexcep
        int duracionciclp = 1; // duracionexcep
        if(this.validateInputs()){
            int duracionnt = Integer.parseInt(duracion.getText()); // duracion
            if(this.tipoproceso.getSelectedIndex()==1){
                ciclo = Integer.parseInt(cicloexcep.getText()); // cicloexcep
                duracionciclp = Integer.parseInt(duracionexcep.getText()); // duracionexcep
            }
            Proceso p = new Proceso(listolista.getSize(),nombre.getText(),(String) this.tipoproceso.getSelectedItem(),duracionnt,ciclo,duracionciclp,0);
            listolista.appendLast(p);
            todos.appendLast(p);
            this.uPcbs();
            
        }else{
            javax.swing.JOptionPane.showMessageDialog(null, "error en los atributos del proceso");
        }
    }//GEN-LAST:event_guardarprocesoActionPerformed

    private void tipoprocesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoprocesoActionPerformed
        // TODO add your handling code here:
        if(tipoproceso.getSelectedIndex() == 1){
            this.duracionexcep.setEnabled(true);
            this.cicloexcep.setEnabled(true);
        }else{
            this.duracionexcep.setEnabled(false);
            this.cicloexcep.setEnabled(false);
        }
    }//GEN-LAST:event_tipoprocesoActionPerformed

    private void tiempoinstruccionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tiempoinstruccionStateChanged
        // TODO add your handling code here:
        this.jLabel16.setText(this.tiempoinstruccion.getValue()+" ms");
        int[] h = {this.tiempoinstruccion.getValue(),this.politica.getSelectedIndex()};
        ProcesoJsonHandler.saveToJson(h, "numbers.json");
    }//GEN-LAST:event_tiempoinstruccionStateChanged

    private void politicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_politicaActionPerformed
        // TODO add your handling code here:
        int[] h = {this.tiempoinstruccion.getValue(),this.politica.getSelectedIndex()};
        ProcesoJsonHandler.saveToJson(h, "numbers.json");
    }//GEN-LAST:event_politicaActionPerformed

    private void pcbsCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_pcbsCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_pcbsCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaSimulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea bloqueados;
    private javax.swing.JTextField cicloexcep;
    private javax.swing.JTextArea cpu1;
    private javax.swing.JTextArea cpu2;
    private javax.swing.JTextArea cpu3;
    private javax.swing.JTextField duracion;
    private javax.swing.JTextField duracionexcep;
    private javax.swing.JButton guardarproceso;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea listos;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextArea pcbs;
    private javax.swing.JComboBox<String> politica;
    private javax.swing.JTextField relojglobal;
    private javax.swing.JTextArea salida;
    private javax.swing.JSlider tiempoinstruccion;
    private javax.swing.JComboBox<String> tipoproceso;
    private javax.swing.JTextField unidadescpu;
    // End of variables declaration//GEN-END:variables
}
