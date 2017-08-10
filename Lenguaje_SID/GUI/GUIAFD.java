package Lenguaje_SID.GUI;

//Importando la carpeta AFD dentro del desarrollo del lenguaje
import Lenguaje_SID.AFD.analizadorSintactico;
import Lenguaje_SID.AFD.AFD;
import Lenguaje_SID.AFD.listaSimbolos;
import Lenguaje_SID.AFD.token;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.Action;

/**
 *
 * @author erick
 */
public class GUIAFD extends javax.swing.JFrame {

    //variables de clase
    Action Copiar_Action, Pegar_Action, Cortar_Action;
    Informacion form;
    /*
    Importaciones para las partes del analisis del código
     */
    private AFD analisisLexico;
    private analizadorSintactico analizadorSintactico;


    /*
    Listas de arreglos para distintos propositos
     */
    private ArrayList<token> listaTokens;
    private ArrayList<String> listaErroresAnalisisLexico;
    private ArrayList<listaSimbolos> listaSimbolos;
    private ArrayList<String> listaErroresSintactico;

    //Variable para leer los archivos desde codigo
    private File archivo_guardado;

    //Variables para llevar el control de las cosas
    protected boolean modificaciones = false, fileSaved = false;
    private boolean aceptado;

    //Arreglos estáticos solo para pruebas y poder ingresarlas a tablas
    private Object[] titulosTablaTokens, titulosTablaDeSimbolos;
    private Object[][] informacionTablaSimbolos, informacionTablaTokens;

    //Modelo para ambas tablas del panel 2 y 3
    private DefaultTableModel modelo;

    public GUIAFD() {
        initComponents();
        this.setLocationRelativeTo(null);

        //Declaracion de los titulos para la tabla de Tokens del panel dos
        titulosTablaTokens = new String[5];
        //Titulos de la tabla de tokens
        titulosTablaTokens[0] = "Numero";
        titulosTablaTokens[1] = "Token";
        titulosTablaTokens[2] = "Valor";
        titulosTablaTokens[3] = "Linea";
        titulosTablaTokens[4] = "Columna";

        //Declaracion de los títulos para la tabla de simbolos
        titulosTablaDeSimbolos = new Object[6];
        //Llenado de los titulos de la tabla para la tabla de simbolos
        titulosTablaDeSimbolos[0] = "Numero";
        titulosTablaDeSimbolos[1] = "Clase";
        titulosTablaDeSimbolos[2] = "Nombre del valor";
        titulosTablaDeSimbolos[3] = "Tipo de dato";
        titulosTablaDeSimbolos[4] = "Valor";
        titulosTablaDeSimbolos[5] = "Disponibilidad";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        Columnas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Lineas = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        subCopiar = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        subCortar = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        subPegar = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(360);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("DialogInput", 0, 13)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextArea1CaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("Código fuente:");

        jLabel3.setText("Columna:");

        jLabel5.setText("Linea:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(Lineas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(Columnas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Lineas, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(Columnas, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel5);

        jTextArea2.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Resultados:", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lista de tokens", jPanel2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lista de simbolos", jPanel3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jSplitPane1.setRightComponent(jPanel6);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/carpeta (1).png"))); // NOI18N
        jMenu1.setText("Archivo");
        jMenu1.add(jSeparator1);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Abrir.png"))); // NOI18N
        jMenuItem6.setText("Abrir");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);
        jMenu1.add(jSeparator2);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancelar.png"))); // NOI18N
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/documento.png"))); // NOI18N
        jMenu6.setText("Editar");
        jMenu6.add(jSeparator5);

        subCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        subCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/copiar.png"))); // NOI18N
        subCopiar.setText("Copiar");
        subCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subCopiarActionPerformed(evt);
            }
        });
        jMenu6.add(subCopiar);
        jMenu6.add(jSeparator6);

        subCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        subCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/tijeras.png"))); // NOI18N
        subCortar.setText("Cortar");
        subCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subCortarActionPerformed(evt);
            }
        });
        jMenu6.add(subCortar);
        jMenu6.add(jSeparator7);

        subPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        subPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pegar.png"))); // NOI18N
        subPegar.setText("Pegar");
        subPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subPegarActionPerformed(evt);
            }
        });
        jMenu6.add(subPegar);

        jMenuBar1.add(jMenu6);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/play.png"))); // NOI18N
        jMenu5.setText("Compilar");
        jMenu5.add(jSeparator14);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/jugar.png"))); // NOI18N
        jMenuItem5.setText("Análisis lexico");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);
        jMenu5.add(jSeparator9);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/jugar.png"))); // NOI18N
        jMenuItem7.setText("Análisis sintactico");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);
        jMenu5.add(jSeparator10);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/jugar.png"))); // NOI18N
        jMenuItem8.setText("Análisis semántico");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);

        jMenuBar1.add(jMenu5);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Acerca.png"))); // NOI18N
        jMenu2.setText("Acerca de");
        jMenu2.add(jSeparator13);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/gestion-de-datos-simbolo-de-interfaz-con-engranajes-y-numeros-de-codigo-binario.png"))); // NOI18N
        jMenuItem1.setText("Codigo fuente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        modelo = new DefaultTableModel();
        jTable1.setModel(modelo);
        if (!jTextArea1.getText().isEmpty()) {
            ejecutarCodigo(jTextArea1.getText());
        } else {
            JOptionPane.showMessageDialog(this, "El código está vacio", "No hay codigo a compilar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        OpenFile();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        modelo = new DefaultTableModel();
        jTable2.setModel(modelo);
        ejecutaAnalisisSintactico();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        JOptionPane.showMessageDialog(this, "Funcion no disponible", "En proceso", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void subCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subCortarActionPerformed
        Cortar_Action.actionPerformed(evt);//llama accion
    }//GEN-LAST:event_subCortarActionPerformed

    private void subCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subCopiarActionPerformed
        Copiar_Action.actionPerformed(evt); //llama accion      
    }//GEN-LAST:event_subCopiarActionPerformed

    private void subPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subPegarActionPerformed
        Pegar_Action.actionPerformed(evt);//llama accion
    }//GEN-LAST:event_subPegarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        form = new Informacion();
        form.setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextArea1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextArea1CaretUpdate
         int posicion = evt.getDot();
        try {
            int row = jTextArea1.getLineOfOffset(posicion) + 1;
            int col = posicion - jTextArea1.getLineStartOffset(row - 1);
            Lineas.setText(row + "");  //imprime en que linea esta el puntero
            Columnas.setText(col + ""); //imprime en que columna esta el puntero
        } catch (Exception r) {
        }
    }//GEN-LAST:event_jTextArea1CaretUpdate

    //revisa que el archivo que se va a abrir sea de tipo de dato que se está pidiendo
    private void OpenFile() {
        JFileChooser FileChooser = new JFileChooser("codigos_SID/");
        FileChooser.setFileFilter(new FileNameExtensionFilter("todos los archivos *.sid", "sid", "sid"));
        FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int selection = FileChooser.showOpenDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            archivo_guardado = FileChooser.getSelectedFile();
            boolean name_file = archivo_guardado.getAbsolutePath().endsWith(".sid");
            if (name_file) {
                jTextArea2.setText("");
                modelo = new DefaultTableModel();
                jTable1.setModel(modelo);
                jTable2.setModel(modelo);
                jTextArea1.setText(getArchivo(archivo_guardado.getAbsolutePath()));
                if (!jTextArea1.getText().isEmpty()) {
                    modificaciones = true;
                    fileSaved = true;
                }
                //actualizaTree(archivo_guardado.getParent());
            } else {
                JOptionPane.showMessageDialog(this, "Tipo de archivo no admitido", "Error de archivo", JOptionPane.ERROR_MESSAGE);
                OpenFile();
            }

        }
    }

    //Obtiene el archivo despues de comprobar que sea el tipo de dato a leer
    public String getArchivo(String ruta) {
        FileReader fr = null;
        BufferedReader br = null;
        //Cadena de texto donde se guardara el contenido del archivo
        String contenido = "";
        try {
            //ruta puede ser de tipo String o tipo File
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);

            String linea;
            //Obtenemos el contenido del archivo linea por linea
            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }

        } catch (Exception e) {
        } //finally se utiliza para que si todo ocurre correctamente o si ocurre 
        //algun error se cierre el archivo que anteriormente abrimos
        finally {
            try {
                br.close();
            } catch (Exception ex) {
            }
        }
        return contenido;
    }

    /*
    ***********************************************************************************************************
    **************************Ejecuta el analisis lexico del código escrito************************************
    ***********************************************************************************************************
     */
    private void ejecutarCodigo(String texto) {
        analisisLexico = new AFD(texto);
        analisisLexico.estadoInicial();

        listaTokens = analisisLexico.getListaTokens();
        int cont = 0;
        jTextArea2.setText("");

        boolean verdad = listaTokens.isEmpty();

        if (!verdad) {
            jTextArea2.append("\n**Compilación completa del análisis léxico**\n");
            /*
            para crear la tabla de tokens se crea este arreglo bidimencional
            el cual se pasara como attributo al DefaultTableModel
             */
            informacionTablaTokens = new Object[listaTokens.size()][5];

            while (cont < listaTokens.size()) {
                //Llenando el arreglo para la tabla que se verá en el panel 2
                informacionTablaTokens[cont][0] = cont + 1;
                informacionTablaTokens[cont][1] = listaTokens.get(cont).getToken();
                informacionTablaTokens[cont][2] = listaTokens.get(cont).getValor();
                informacionTablaTokens[cont][3] = listaTokens.get(cont).getLinea();
                informacionTablaTokens[cont][4] = listaTokens.get(cont).getColumna();
                //Fin del llenado del arreglo

                /*
                if (listaTokens.get(cont).getToken().equals("llaveApertura") || listaTokens.get(cont).getToken().equals("Delimitador")) {
                    jTextArea2.append(" -> " + listaTokens.get(cont).getToken() + "(" + listaTokens.get(cont).getValor() + ", En la linea " + listaTokens.get(cont).getLinea() + " En la columna " + listaTokens.get(cont).getColumna() + ")");
                    jTextArea2.append("\n");
                    cont++;
                } else {
                    jTextArea2.append(" -> " + listaTokens.get(cont).getToken() + "(" + listaTokens.get(cont).getValor() + ", En la linea " + listaTokens.get(cont).getLinea() + " En la columna " + listaTokens.get(cont).getColumna() + ")");

                    cont++;
                }
                 */
                cont++;
            }

            //Creando la tabla
            modelo = new DefaultTableModel(informacionTablaTokens, titulosTablaTokens);

            jTable1.setModel(modelo);
            //Para cambiar el tamaño de la columna 1
            TableColumn columna = jTable1.getColumn("Numero");
            columna.setPreferredWidth(10);
        } else {
            jTextArea2.append("\n**No se han encontrado tokens resultanes del análisis léxico**\n");
        }
    }

    /*
    ***********************************************************************************************************
    **************************Ejecuta el analisis sintactico del código escrito********************************
    ***********************************************************************************************************
     */
    private void ejecutaAnalisisSintactico() {

        listaErroresAnalisisLexico = analisisLexico.getListaErrores();
        boolean vacio = listaErroresAnalisisLexico.isEmpty();
        if (vacio) {
            if (!listaTokens.isEmpty()) {
                analizadorSintactico = new analizadorSintactico(listaTokens);
                analizadorSintactico.Programa();
                listaSimbolos = analizadorSintactico.getListaSimbolos();
                listaErroresSintactico = analizadorSintactico.getListaErrores();

                jTextArea2.append("\n\n\n\tLISTA DE SIMBOLOS");
                if (!listaSimbolos.isEmpty()) {
                    /*
                Inicializacion del arreglo bidimencional para el panel 3
                     */
                    informacionTablaSimbolos = new Object[listaSimbolos.size()][6];

                    for (int i = 0; i < listaSimbolos.size(); i++) {
                        //Llenando la informacion de la tabla de simbolos del panel 3
                        informacionTablaSimbolos[i][0] = i + 1;
                        informacionTablaSimbolos[i][1] = listaSimbolos.get(i).getClase();
                        informacionTablaSimbolos[i][2] = listaSimbolos.get(i).getNombreValor();
                        informacionTablaSimbolos[i][3] = listaSimbolos.get(i).getTipoDeDato();
                        informacionTablaSimbolos[i][4] = listaSimbolos.get(i).getValor();
                        informacionTablaSimbolos[i][5] = listaSimbolos.get(i).getDisponibilidad();
                        //Fin del llenado de datos del arreglo para la tabla de simbolos

                        jTextArea2.append("\n\n\nClase -> " + listaSimbolos.get(i).getClase() + "\tDisponibilidad -> "
                                + listaSimbolos.get(i).getDisponibilidad() + "\tNombre -> "
                                + listaSimbolos.get(i).getNombreValor() + "\tTipo de dato -> "
                                + listaSimbolos.get(i).getTipoDeDato() + "\tValor -> "
                                + listaSimbolos.get(i).getValor());
                    }

                    //Creación del DefaultTableModel para el panel 3
                    modelo = new DefaultTableModel(informacionTablaSimbolos, titulosTablaDeSimbolos);
                    //Envio de datos a la última tabla
                    jTable2.setModel(modelo);

                }

                jTextArea2.append("\n\n");
                jTextArea2.append("\n\n\n\tLISTA DE ERRORES");

                if (!listaErroresSintactico.isEmpty()) {
                    for (int i = 0; i < listaErroresSintactico.size(); i++) {
                        jTextArea2.append("\n\n\nError --> " + listaErroresSintactico.get(i));
                    }
                } else {
                    jTextArea2.append("\n\n\nNo hay Errores\n\n");
                }

            } else {
                JOptionPane.showMessageDialog(this, "No hay tokens disponibles para realizar el análisis léxico", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tal parece que se encontraron algunos errores en el análisis léxico", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIAFD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Columnas;
    private javax.swing.JLabel Lineas;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JMenuItem subCopiar;
    private javax.swing.JMenuItem subCortar;
    private javax.swing.JMenuItem subPegar;
    // End of variables declaration//GEN-END:variables
}
