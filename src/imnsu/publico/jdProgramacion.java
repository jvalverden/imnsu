package imnsu.publico;

import Dao.personaDao;
import Dao.personaTipoEstudioDao;
import Vo.PersonaVo;
import Vo.personatipoestudioVo;
import Vo.tipoEstadoVo;
import Vo.tipoEstudioVo;
import Vo.vpersonaestudioVo;
import java.util.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.*;

import java.awt.event.*;
import java.sql.Time;
import resources.img.ArrayListComboBoxModel;

public class jdProgramacion extends javax.swing.JDialog {

    private Object[] nombreColumnas;
    private Object[][] fechas;
    private Object[][] acumulados;
    private final int idExpediente;
    private final int idUnidad;
    private final boolean cancelarAudienciasPrevias;
    private PersonaVo persona;
    private boolean sintabla;

    /**
     *
     * @param parent
     * @param modal
     * @param expediente
     * @param unidad
     * @param cancelarAudienciasPrevias
     * @param jpactos
     */
    public jdProgramacion(java.awt.Frame parent, boolean modal, String expediente, String unidad, boolean cancelarAudienciasPrevias, PersonaVo persona, boolean sintabla) {
        super(parent, modal);
        initComponents();
        this.persona = persona;
        if (this.persona != null) {
            jLblIdNurej.setText(persona.getPaterno() + " " + persona.getMaterno() + " " + persona.getNombre());
        }
        this.idExpediente = Integer.parseInt(expediente);
        this.idUnidad = Integer.parseInt(unidad);
        this.cancelarAudienciasPrevias = cancelarAudienciasPrevias;
        this.jXDtPFecha.setDate(new Date());
//        this.jCbSala.setSelectedIndex(0);

        String nurej = "";//eExpediente1.getNurej(this.idExpediente);

        this.cargarComboSalas();
        Date fecha = this.jXDtPFecha.getDate();
        Object item = null;//this.jCbSala.getSelectedItem();
        int idSala = 0;// Integer.parseInt(((eComboItem)item).getId());
        this.setLocationRelativeTo(parent);
        this.buscar(fecha, idSala);
        this.sintabla = sintabla;
        if (this.sintabla) {
            this.setSize(680, 430);
            jpTabla.setVisible(false);
        } else {
            this.setSize(680, 650);
        }
        this.setLocationRelativeTo(parent);
        setCbTipoEstudio();
        setTablaListaPersona("", "", "", " and enespera=true ");
        setCbTipoEstado();
    }

    private void setCbTipoEstado() {
        jcbTipoEstado.removeAll();
        ArrayListComboBoxModel model = new ArrayListComboBoxModel(tipoEstadoDao1.comboTipoEstado());
        jcbTipoEstado.setModel(model);
        DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof tipoEstadoVo) {
                    tipoEstadoVo tipoestadoVo = (tipoEstadoVo) value;
                    setText(tipoestadoVo.getDescripcion());
                }
                return this;
            }
        };
        jcbTipoEstado.setRenderer(renderer);
        jcbTipoEstado.updateUI();
    }

    private void setCbTipoEstudio() {
        jcbTipoEstudio.removeAll();
        ArrayListComboBoxModel model = new ArrayListComboBoxModel(tipoEstudioDao1.comboTipoEstudio());
        jcbTipoEstudio.setModel(model);
        DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof tipoEstudioVo) {
                    tipoEstudioVo tipoestadoVo = (tipoEstudioVo) value;
                    setText(tipoestadoVo.getDescripcion());
                }
                return this;
            }
        };
        jcbTipoEstudio.setRenderer(renderer);
        jcbTipoEstudio.updateUI();
    }
    // Accion del menu de Expedientes.
    ActionListener menuListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
            String expediente = event.getActionCommand();
            if (expediente.contains("PROG")) {
                String[] partes = expediente.split(",");
                String fecha = partes[1];
                String hora = partes[2];
                programarAudiencia(fecha, hora);
            } else {
                String[] partesExpediente = expediente.split(",");
                String idexpediente = partesExpediente[0].substring(1);
                String idaudiencia = partesExpediente[1];
                String hora = partesExpediente[2].substring(0, partesExpediente[2].length() - 1);
                verDetalleAudiencia(idexpediente, idaudiencia);
            }
        }
    };

    /**
     * Metodo que carga el combo de salas.
     */
    private void cargarComboSalas() {
        ArrayList registro = new ArrayList();
//        this.jCbSala.removeAllItems();
        ArrayList listaSalas = null;//eSala1.obtenerSala(this.idUnidad); 
//        for(int i=0;i<listaSalas.size();i++)
//        {
//            registro=(ArrayList)listaSalas.get(i);
////            this.jCbSala.addItem(new eComboItem(registro.get(0).toString(), registro.get(1).toString()));            
//        }
    }

    /**
     * Metodo que obtiene el modelo.
     *
     * @param fecha
     * @param idSala
     */
    private void obtenerModelo(Date fecha, int idSala) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYY");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String[] nombreDias = {"Lu", "Ma", "Mi", "Ju", "Vi", "Sá", "Do"};

        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int diasParaLunes = dayOfWeek == 1 ? -6 : (2 - dayOfWeek);
        int diasParaDomigo = dayOfWeek == 1 ? 0 : ((7 - dayOfWeek) + 1);

        c.add(Calendar.DATE, diasParaLunes);
        Date lunes = c.getTime();

        c.setTime(fecha);
        c.add(Calendar.DATE, diasParaDomigo);
        Date domingo = c.getTime();

//        eAudiencia audiencia = new eAudiencia();
        ArrayList<personatipoestudioVo> listapersonatipoestudio = personaTipoEstudioDao.listaPersonatipoEstudio();// null;//null;// audiencia.obtenerAudiencias(dateFormat.format(lunes), dateFormat.format(domingo), idUnidad, idSala);

        this.nombreColumnas = new String[8];
        this.nombreColumnas[0] = "Horas";

        for (int i = 0; i < 7; i++) {
            c.setTime(lunes);
            c.add(Calendar.DATE, i);
            Date dia = c.getTime();
            String fechaTitulo = dateFormat.format(dia);

            this.nombreColumnas[i + 1] = fechaTitulo;

        }

        int cantidadFilas = 13;
        int cantidadColumnas = 8;
        int horaInicio = 7;
        this.fechas = new Object[cantidadFilas][cantidadColumnas];
        this.acumulados = new Object[cantidadFilas][cantidadColumnas];
        int hora = horaInicio;
        for (int fila = 0; fila < cantidadFilas; fila++) {
            String[] nuevaFila = new String[cantidadColumnas];
            for (int col = 0; col < cantidadColumnas; col++) {
                c.setTime(lunes);
                c.add(Calendar.DATE, col - 1);
                Date dia = c.getTime();
                if (col == 0) {
                    nuevaFila[col] = hora + ":00 - " + (hora + 1) + ":00";
                } else {
                    String fechaColumna = this.nombreColumnas[col].toString();
                    StringBuilder cadena = new StringBuilder();
                    // listapersonatipoestudio=personaTipoEstudioDao.listaPersonatipoEstudioFechaHora(String.valueOf(dia), hora + ":00");

                    for (personatipoestudioVo personatipoestudio : listapersonatipoestudio) {
                        Date f = new Date(personatipoestudio.getFechaconfirmada().getTime());
                        String f1 = String.valueOf(f.getDate()) + "-" + String.valueOf(f.getMonth() + 1) + "-" + String.valueOf(f.getYear() + 1900);
                        String f2 = String.valueOf(dia.getDate()) + "-" + String.valueOf(dia.getMonth() + 1) + "-" + String.valueOf(dia.getYear() + 1900);
                        if (((personatipoestudio.getHorainicio().compareTo(new Time(hora, 0, 0))) == 0) && (f1.compareTo(f2) == 0)) {

                            String horas = String.valueOf(personatipoestudio.getHorainicio()) + "-" + String.valueOf(personatipoestudio.getHorafin());
                            cadena.append("[" + personatipoestudio.getMolibdeno() + "," + personatipoestudio.getTecnecio() + "," + horas + "]|");

                        }
                    }

                    if (cadena.length() > 0) {
                        nuevaFila[col] = cadena.toString();
                    } else {
                        nuevaFila[col] = "Libre";
                    }

                    cadena.setLength(0);
                }
            }

            hora++;
            this.fechas[fila] = nuevaFila;
        }
    }

    /**
     * Metodo que realizar la busqueda de las audiencias programas por Fecha y
     * Sala.
     *
     * @param fecha
     * @param idSala
     */
    private void buscar(Date fecha, int idSala) {
        this.obtenerModelo(fecha, idSala);
        final JTable table = new JTable(this.fechas, this.nombreColumnas) {
            @Override
            /**
             * Metodo que habilita o no la edicion de las celdas.
             */
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //// Asigna un progresbar a cada celda.
        for (int i = 1; i < this.nombreColumnas.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new ProgressCellRender());
        }

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowHeight(25);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    String value = target.getValueAt(row, column).toString();
                    String fecha = target.getColumnName(column);
//                    if (value.equals("Libre")) {
                    String hora = target.getValueAt(row, 0).toString();
                    programarAudiencia(fecha, hora);
//                    } else {
//                        String[] expedientes = value.split("\\|");
//                        JPopupMenu pm = new JPopupMenu();
//                        long totalMinutos = 0;
//                        long diferencia = 0;
//                        DateFormat formatter = new SimpleDateFormat("HH:mm");
//
//                        for (String expediente : expedientes) {
//                            String[] partesExpediente = expediente.split(",");
//                            String idexpediente = partesExpediente[0].substring(1);
//                            String idaudiencia = partesExpediente[1];
//                            String hora = partesExpediente[2].substring(0, partesExpediente[2].length() - 1);
//                            String nurej = "";//eExpediente1.getNurej(Integer.parseInt(idexpediente)).trim();
//                            JMenuItem item = new JMenuItem("Nurej:" + nurej + "  " + hora);
//                            item.setActionCommand(expediente);
//                            item.addActionListener(menuListener);
//                            pm.add(item);
//                        }
//
//                        totalMinutos = (long) acumulados[row][column];
//                        if (totalMinutos < 60) {
//                            String hora = target.getValueAt(row, 0).toString();
//                            JMenuItem item = new JMenuItem("Programar");
//                            item.setActionCommand("PROG," + fecha + "," + hora);
//                            item.addActionListener(menuListener);
//                            pm.add(item);
//                        }
//
//                        //// Desplegar el menu.
//                        if (!pm.isPopupTrigger(e)) {
//                            Point p = e.getPoint();
//                            int row1 = target.rowAtPoint(p);
//                            int col1 = target.columnAtPoint(p);
//
//                            pm.show(table, p.x, p.y);
//                        }
//                    }
                }
            }
        });

        this.jScrollPane1.setViewportView(table);
    }

    /**
     * Clase ProgressCellRender.
     */
    public class ProgressCellRender extends JProgressBar implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            int progress = 0;
            if (value.toString().equals("Libre")) {
                setValue(progress);
                setStringPainted(true);
                setForeground(Color.GRAY);
                setString("Libre");
                return this;
            } else {
//                long totalMinutos =new Long("0.3333");// (long)acumulados[row][column];
                progress = Math.round(100);//(totalMinutos * 100) / 60);                
                setValue(progress);
                setString("");
                setStringPainted(false);
                return this;
            }
        }
    }

    /**
     * Metodo que despliega el dialogo para programar la audiencia.
     *
     * @param fecha
     * @param hora
     */
    private void programarAudiencia(String fecha, String hora) {
        jdConfirmarEstudio dialog = new jdConfirmarEstudio(new JFrame(), true, this.persona, fecha, hora);
        dialog.show();

        buscar(jXDtPFecha.getDate(), 0);
//        Object item = this.jCbSala.getSelectedItem();
//        int idSala = Integer.parseInt(((eComboItem)item).getId());
//        String sala = ((eComboItem)item).getDescripcion();
//        int idProceso = eExpediente1.getIdProcedimiento(idExpediente);
//        int idMateria = eExpediente1.getIdMateria(idExpediente);
//        jdConfirmarAudiencia jdConfirmar = new jdConfirmarAudiencia(new JFrame(),true, this.idExpediente, idSala, sala, idProceso, idMateria, this.idUnidad, fecha, hora, this.cancelarAudienciasPrevias,this.jpactos);
//        jdConfirmar.setLocationRelativeTo(this);
//        jdConfirmar.show();
//        this.buscar(this.jXDtPFecha.getDate(), idSala);
    }

    /**
     * Metodo que visualiza la audiencia.
     *
     * @param expediente
     * @param audiencia
     */
    private void verDetalleAudiencia(String expediente, String audiencia) {
//        jdDetalleAudiencia jdDetalle = new jdDetalleAudiencia(new JFrame(),true, Integer.parseInt(expediente), Integer.parseInt(audiencia));
//        jdDetalle.setLocationRelativeTo(this);
//        jdDetalle.show();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoEstudioDao1 = new Dao.tipoEstudioDao();
        vpersonaestudioDao1 = new Dao.vpersonaestudioDao();
        tipoEstadoDao1 = new Dao.tipoEstadoDao();
        jPnlCabecera = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLblNurej = new javax.swing.JLabel();
        jLblIdNurej = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jXDtPFecha = new org.jdesktop.swingx.JXDatePicker();
        jLblFecha = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpTabla = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jcbTipoEstudio = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jchs1 = new javax.swing.JCheckBox();
        jchs2 = new javax.swing.JCheckBox();
        jchTipoest = new javax.swing.JCheckBox();
        jchInterior = new javax.swing.JCheckBox();
        jchEstado = new javax.swing.JCheckBox();
        jcbTipoEstado = new javax.swing.JComboBox();
        jPanel14 = new javax.swing.JPanel();
        jchfas = new javax.swing.JCheckBox();
        jchfdes = new javax.swing.JCheckBox();
        jPanel18 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jxtlistaPersona = new org.jdesktop.swingx.JXTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Programación de Estudio");

        jPnlCabecera.setPreferredSize(new java.awt.Dimension(401, 25));
        jPnlCabecera.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLblNurej.setText("Persona:  ");
        jPanel3.add(jLblNurej, java.awt.BorderLayout.WEST);

        jLblIdNurej.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLblIdNurej.setText("[PERSONA]");
        jPanel3.add(jLblIdNurej, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPnlCabecera.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel5.setLayout(new java.awt.GridLayout(1, 1));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jXDtPFecha.setToolTipText("Fecha de Audiencia");
        jXDtPFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDtPFechaActionPerformed(evt);
            }
        });
        jPanel2.add(jXDtPFecha, java.awt.BorderLayout.CENTER);

        jLblFecha.setText("Fecha:");
        jPanel2.add(jLblFecha, java.awt.BorderLayout.WEST);

        jPanel5.add(jPanel2);

        jPnlCabecera.add(jPanel5, java.awt.BorderLayout.EAST);

        getContentPane().add(jPnlCabecera, java.awt.BorderLayout.NORTH);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(1, 8));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel8);

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lunes");
        jPanel6.add(jLabel1);

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Martes");
        jPanel6.add(jLabel2);

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Miércoles");
        jPanel6.add(jLabel3);

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Jueves");
        jPanel6.add(jLabel4);

        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Viernes");
        jPanel6.add(jLabel5);

        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Sábado");
        jPanel6.add(jLabel6);

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Domingo");
        jPanel6.add(jLabel7);

        jPanel7.add(jPanel6, java.awt.BorderLayout.NORTH);
        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

        jpTabla.setPreferredSize(new java.awt.Dimension(727, 290));
        jpTabla.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("OPTIMIZACION DE REGISTRO"));
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(195, 180));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.GridLayout(2, 1));

        jPanel9.setLayout(new java.awt.BorderLayout());

        jcbTipoEstudio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbTipoEstudio.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jcbTipoEstudioPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jcbTipoEstudio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcbTipoEstudioKeyReleased(evt);
            }
        });
        jPanel9.add(jcbTipoEstudio, java.awt.BorderLayout.CENTER);

        jLabel9.setText("Tipo Estudio: ");
        jPanel9.add(jLabel9, java.awt.BorderLayout.WEST);

        jPanel12.add(jPanel9);

        jPanel8.add(jPanel12, java.awt.BorderLayout.NORTH);

        jPanel16.setLayout(new java.awt.BorderLayout());

        jPanel17.setLayout(new java.awt.GridLayout(2, 1));

        jchs1.setText("Primera Semana");
        jchs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchs1ActionPerformed(evt);
            }
        });
        jPanel11.add(jchs1);

        jchs2.setText("Segunda Semana");
        jchs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchs2ActionPerformed(evt);
            }
        });
        jPanel11.add(jchs2);

        jchTipoest.setText("Tipo de Estudio");
        jPanel11.add(jchTipoest);

        jchInterior.setText("Interior");
        jPanel11.add(jchInterior);

        jchEstado.setText("Condición");
        jchEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchEstadoActionPerformed(evt);
            }
        });
        jPanel11.add(jchEstado);

        jcbTipoEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel11.add(jcbTipoEstado);

        jPanel17.add(jPanel11);

        jchfas.setText("Fecha I.E. Ascendente");
        jchfas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchfasActionPerformed(evt);
            }
        });
        jPanel14.add(jchfas);

        jchfdes.setText("Fecha I.E. Descendente");
        jchfdes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchfdesActionPerformed(evt);
            }
        });
        jPanel14.add(jchfdes);

        jPanel17.add(jPanel14);

        jPanel16.add(jPanel17, java.awt.BorderLayout.CENTER);

        jPanel18.setPreferredSize(new java.awt.Dimension(139, 30));

        jButton3.setText("Selecionar Persona");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton3);

        jButton4.setText("Listar Todo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton4);

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton1);

        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton2);

        jPanel16.add(jPanel18, java.awt.BorderLayout.SOUTH);

        jPanel8.add(jPanel16, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        jpTabla.add(jPanel1, java.awt.BorderLayout.NORTH);
        jPanel1.getAccessibleContext().setAccessibleName("");

        jPanel13.setPreferredSize(new java.awt.Dimension(727, 95));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jxtlistaPersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Sexo", "Edad", "Condición", "Procedencia", "Fecha Llegada", "Estudio", "Obj"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jxtlistaPersona.setMinimumSize(new java.awt.Dimension(240, 0));
        jxtlistaPersona.getTableHeader().setReorderingAllowed(false);
        jxtlistaPersona.setVisibleRowCount(5);
        jxtlistaPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jxtlistaPersonaMouseReleased(evt);
            }
        });
        jxtlistaPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jxtlistaPersonaKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jxtlistaPersona);
        if (jxtlistaPersona.getColumnModel().getColumnCount() > 0) {
            jxtlistaPersona.getColumnModel().getColumn(1).setMinWidth(50);
            jxtlistaPersona.getColumnModel().getColumn(1).setPreferredWidth(50);
            jxtlistaPersona.getColumnModel().getColumn(1).setMaxWidth(50);
            jxtlistaPersona.getColumnModel().getColumn(2).setMinWidth(50);
            jxtlistaPersona.getColumnModel().getColumn(2).setPreferredWidth(50);
            jxtlistaPersona.getColumnModel().getColumn(2).setMaxWidth(50);
            jxtlistaPersona.getColumnModel().getColumn(3).setPreferredWidth(90);
            jxtlistaPersona.getColumnModel().getColumn(7).setMinWidth(0);
            jxtlistaPersona.getColumnModel().getColumn(7).setPreferredWidth(0);
            jxtlistaPersona.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        jPanel13.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jpTabla.add(jPanel13, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpTabla, java.awt.BorderLayout.SOUTH);
        jpTabla.getAccessibleContext().setAccessibleName("");

        getAccessibleContext().setAccessibleName("Estudio");

        setBounds(0, 0, 661, 492);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento ActionPerformed de jXDtPFecha.
     *
     * @param evt
     */
    private void jXDtPFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDtPFechaActionPerformed
        Date fecha = this.jXDtPFecha.getDate();
        Object item = 0;
        int idSala = 0;// Integer.parseInt(((eComboItem)item).getId());
        this.buscar(fecha, idSala);
    }//GEN-LAST:event_jXDtPFechaActionPerformed

    private void jcbTipoEstudioPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jcbTipoEstudioPopupMenuWillBecomeInvisible
        // TODO add your handling code here:

    }//GEN-LAST:event_jcbTipoEstudioPopupMenuWillBecomeInvisible

    private void jcbTipoEstudioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbTipoEstudioKeyReleased
        // TODO add your handling code here:
        tipoEstudioVo tipoestudio = (tipoEstudioVo) jcbTipoEstudio.getSelectedItem();
    }//GEN-LAST:event_jcbTipoEstudioKeyReleased

    private void setMotordeInferencia(boolean testudio, tipoEstudioVo tipoestudio, boolean interior, boolean estado, tipoEstadoVo tipoestado, boolean fas, boolean fes, boolean s1, boolean s2) {
        eliminartodaslasfilastabla(jxtlistaPersona);

        Vector v;
        for (PersonaVo persona : personaDao.getListaPersonaInferencia(testudio, tipoestudio, interior, estado, tipoestado, fas, fes, s1, s2)) {
            v = new Vector();
            v.add(persona.getPaterno() + " " + persona.getMaterno() + " " + persona.getNombre());
            v.add(persona.getSex());
            v.add(persona.getEdad());
            v.add(persona.getTipoestado());
            v.add(persona.getProcedencia());
            v.add(persona.getFecha());
            tipoEstudioVo estudio = tipoEstudioDao1.getTipoEstudio(persona.getIdtipoestudiosolicitado());
            v.add(estudio.getDescripcion());

            v.add(persona);
            agregar_fila_tabla(v, jxtlistaPersona);
        }
        if (jxtlistaPersona.getRowCount() > 0) {
            jxtlistaPersona.setRowSelectionInterval(0, 0);
            cargardatos();
            PersonaVo persona = (PersonaVo) jxtlistaPersona.getModel().getValueAt(jxtlistaPersona.getSelectedRow(), 7);
            setTablaListaPersonaEstudios(persona);
        }
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tipoEstudioVo tipoestudio = (tipoEstudioVo) jcbTipoEstudio.getSelectedItem();
        tipoEstadoVo estado = (tipoEstadoVo) jcbTipoEstado.getSelectedItem();
        setMotordeInferencia(jchTipoest.isSelected(), tipoestudio, jchInterior.isSelected(), jchEstado.isSelected(), estado, jchfas.isSelected(), jchfdes.isSelected(), jchs1.isSelected(), jchs2.isSelected());

        /*tipoEstudioVo tipoestudio=(tipoEstudioVo)jcbTipoEstudio.getSelectedItem();
         personatipoestudioVo personatipoestudio=new personatipoestudioVo();
         personatipoestudio.setIdpersona(persona.getId());
         personatipoestudio.setIdtipoestudio(tipoestudio.getId());
         personatipoestudio.setHorainyeccion(new Time(((java.util.Date)this.h1.getHora()).getTime()));
         personatipoestudio.setIdrecepciongenerador(this.recepcion.getId());
         personatipoestudio.setMobildeno(Float.valueOf(jtMolibdeno.getModel().getValueAt(fila, columna).toString()));
         personatipoestudio.setTecnecio(Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
         personatipoestudio.setFila(fila);
         personatipoestudio.setColumna(columna);
         String[] parts = this.hora.split("-");
         String part1 = parts[0].trim();
         parts = part1.split(":");
         int hh=Integer.valueOf(parts[0].trim());
         int mm=Integer.valueOf(parts[1].trim());
         Time hh1=new Time(hh, mm, 0);
         Time hh2=new Time(hh+1, mm, 0);
         personatipoestudio.setHorainicio(hh1);
         personatipoestudio.setHorafin(hh2);
         this.persona.setEnespera(false);
         parts = this.fecha.split("-");
         String dia = parts[0]; // 004
         String mes = parts[1];
         String anno = parts[2];
         Timestamp feccha=new Timestamp(Integer.valueOf(anno)-1900,Integer.valueOf(mes)-1,Integer.valueOf(dia), ((java.util.Date)this.h1.getHora()).getHours(), ((java.util.Date)this.h1.getHora()).getMinutes(), ((java.util.Date)this.h1.getHora()).getSeconds(),0);
         personatipoestudio.setFechaconfirmada(feccha);
         personaDao.updatePersonaEstudio(persona);
         personaTipoEstudioDao1.insertPersonaTipoestudio(personatipoestudio);
         dispose();
         */
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void cargardatos() {

//        PersonaVo persona = (PersonaVo) jxtlistaPersona.getValueAt(jxtlistaPersona.getSelectedRow(), 4);
//        jtfNombre.setEditable(false);
//        jtfPaterno.setEditable(false);
//        jtfMaterno.setEditable(false);
//        jchOrigen.setEnabled(false);
//        jtfOrigen.setEditable(false);
//        jtfOrigen.setEditable(false);
//        jtfTelefono.setEditable(false);
//        jtffonoreponsalble.setEditable(false);
//        jtfMedico.setEditable(false);
//        jtfInstitucion.setEditable(false);
//        jchEnespera.setEnabled(false);
//        jxdpFechaEspera.setEditable(false);
//        jxdpFechaTentativa.setEditable(false);
//
//        jtfNombre.setText(persona.getNombre());
//        jtfPaterno.setText(persona.getPaterno());
//        jtfMaterno.setText(persona.getMaterno());
//        /*            jftfEdad.setText(String.valueOf(persona.getEdad()));
//         jcbTipoEstado.setSelectedItem(persona.getTipoestado());
//         jcbSexo.setSelectedItem(persona.getSex());
//         tipoEstadoVo tipoestado=tipoEstadoDao1.getTipoEstado(persona.getIdtipoestado());
//         //jcbTipoEstado.setSelectedIndex(0);
//         jcbTipoEstado.getModel().setSelectedItem(tipoestado);*/
//        jchOrigen.setSelected(persona.isInterior());
//        jtfOrigen.setEnabled(persona.isInterior());
//        jtfOrigen.setText(persona.getProcedencia());
//        jtfTelefono.setText(persona.getTelefono());
//        jtffonoreponsalble.setText(persona.getTelefonoresponsable());
//        jtfMedico.setText(persona.getMedicoremitente());
//        jtfInstitucion.setText(persona.getInstituicionprocedente());
//        jchEnespera.setSelected(persona.isEnespera());
//        jxdpFechaEspera.setDate(persona.getFechainicioespera());
//        jxdpFechaTentativa.setDate(persona.getFechatentativa());
    }

    private void eliminarfilastabla(javax.swing.JTable tabla, int i) {
        ((DefaultTableModel) tabla.getModel()).removeRow(i);
    }

    private void eliminartodaslasfilastabla(javax.swing.JTable tabla) {
        while (tabla.getRowCount() > 0) {
            ((DefaultTableModel) tabla.getModel()).removeRow(tabla.getRowCount() - 1);
        }
    }

    public void agregar_fila_tabla(Vector fila, javax.swing.JTable tabla) {
        ((DefaultTableModel) tabla.getModel()).addRow(fila);
    }

    private void setTablaListaPersona(String nombre, String paterno, String materno, String espera) {
        eliminartodaslasfilastabla(jxtlistaPersona);
        Vector v;
        for (PersonaVo persona : personaDao.getListaPersona(nombre, paterno, materno, espera)) {
            v = new Vector();
            v.add(persona.getPaterno() + " " + persona.getMaterno() + " " + persona.getNombre());
            v.add(persona.getSex());
            v.add(persona.getEdad());
            v.add(persona.getTipoestado());
            v.add(persona.getProcedencia());
            v.add(persona.getFecha());
            tipoEstudioVo estudio = tipoEstudioDao1.getTipoEstudio(persona.getIdtipoestudiosolicitado());
            v.add(estudio.getDescripcion());
            v.add(persona);
            agregar_fila_tabla(v, jxtlistaPersona);
        }
        if (jxtlistaPersona.getRowCount() > 0) {
            jxtlistaPersona.setRowSelectionInterval(0, 0);
//            cargardatos();
//            PersonaVo persona = (PersonaVo) jxtlistaPersona.getModel().getValueAt(jxtlistaPersona.getSelectedRow(), 4);
//            setTablaListaPersonaEstudios(persona);
        }
    }

    private void setTablaListaPersonaEstudios(PersonaVo persona) {
//        eliminartodaslasfilastabla(jtListaPersonasEstudio);
//        Vector v;
//        for (vpersonaestudioVo vpersonaestudio : vpersonaestudioDao1.getListaPersonaEstudioP(persona)) {
//            v = new Vector();
//            v.add(vpersonaestudio.getExamen());
//            v.add(vpersonaestudio.getHorainyeccion());
//            v.add(vpersonaestudio.getFechaconfirmada());
//            v.add(vpersonaestudio);
//            agregar_fila_tabla(v, jtListaPersonasEstudio);
//        }
//        if (jtListaPersonasEstudio.getRowCount() > 0) {
//            jtListaPersonasEstudio.setRowSelectionInterval(0, 0);
//
//        }
    }

    private void jxtlistaPersonaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jxtlistaPersonaMouseReleased
        // TODO add your handling code here:
        cargardatos();
        PersonaVo persona = (PersonaVo) jxtlistaPersona.getModel().getValueAt(jxtlistaPersona.getSelectedRow(), 7);
        setTablaListaPersonaEstudios(persona);
    }//GEN-LAST:event_jxtlistaPersonaMouseReleased

    private void jxtlistaPersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jxtlistaPersonaKeyReleased
        // TODO add your handling code here:
        cargardatos();
        PersonaVo persona = (PersonaVo) jxtlistaPersona.getModel().getValueAt(jxtlistaPersona.getSelectedRow(), 7);
        setTablaListaPersonaEstudios(persona);
    }//GEN-LAST:event_jxtlistaPersonaKeyReleased

    private void jchfasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchfasActionPerformed
        // TODO add your handling code here:
        if (jchfas.isSelected()) {
            jchfdes.setSelected(false);
        }
    }//GEN-LAST:event_jchfasActionPerformed

    private void jchfdesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchfdesActionPerformed
        // TODO add your handling code here:
        if (jchfdes.isSelected()) {
            jchfas.setSelected(false);
        }
    }//GEN-LAST:event_jchfdesActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        PersonaVo persona = (PersonaVo) jxtlistaPersona.getValueAt(jxtlistaPersona.getSelectedRow(), 7);
        this.persona = persona;
        if (this.persona != null) {
            jLblIdNurej.setText(persona.getPaterno() + " " + persona.getMaterno() + " " + persona.getNombre());
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        setTablaListaPersona("", "", "", " and enespera=true ");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jchs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchs1ActionPerformed
        // TODO add your handling code here:
        if (jchs1.isSelected()) {
            jchs2.setSelected(false);
        }
    }//GEN-LAST:event_jchs1ActionPerformed

    private void jchs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchs2ActionPerformed
        // TODO add your handling code here:
        if (jchs2.isSelected()) {
            jchs1.setSelected(false);
        }
    }//GEN-LAST:event_jchs2ActionPerformed

    private void jchEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchEstadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblFecha;
    private javax.swing.JLabel jLblIdNurej;
    private javax.swing.JLabel jLblNurej;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPnlCabecera;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXDatePicker jXDtPFecha;
    private javax.swing.JComboBox jcbTipoEstado;
    private javax.swing.JComboBox jcbTipoEstudio;
    private javax.swing.JCheckBox jchEstado;
    private javax.swing.JCheckBox jchInterior;
    private javax.swing.JCheckBox jchTipoest;
    private javax.swing.JCheckBox jchfas;
    private javax.swing.JCheckBox jchfdes;
    private javax.swing.JCheckBox jchs1;
    private javax.swing.JCheckBox jchs2;
    private javax.swing.JPanel jpTabla;
    private org.jdesktop.swingx.JXTable jxtlistaPersona;
    private Dao.tipoEstadoDao tipoEstadoDao1;
    private Dao.tipoEstudioDao tipoEstudioDao1;
    private Dao.vpersonaestudioDao vpersonaestudioDao1;
    // End of variables declaration//GEN-END:variables
}
