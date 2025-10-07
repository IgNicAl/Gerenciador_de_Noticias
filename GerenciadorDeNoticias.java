import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class GerenciadorDeNoticias extends JFrame {

    private final List<Noticia> todasAsNoticias = new ArrayList<>();
    private DefaultListModel<Noticia> listModel;
    private JList<Noticia> newsList;
    private JTextField searchField;
    private JComboBox<String> categoryFilter;
    private JButton addButton, editButton, deleteButton;
    private JLabel totalNewsLabel;
    private JLabel statusLabel;

    public GerenciadorDeNoticias() {
        super("Gerenciador de Notícias");
        initUI();
        carregarNoticiasIniciais();
        atualizarLista("");
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Buscar"));
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Buscar");
        JButton clearButton = new JButton("Limpar");
        searchPanel.add(new JLabel("Título:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(clearButton);

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Notícias"));

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        String[] categories = {"Todas", "Tecnologia", "Esportes", "Política", "Economia", "Saúde"};
        categoryFilter = new JComboBox<>(categories);
        filterPanel.add(new JLabel("Categoria:"));
        filterPanel.add(categoryFilter);
        centerPanel.add(filterPanel, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        newsList = new JList<>(listModel);
        newsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(newsList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addButton = new JButton("[Adicionar]");
        editButton = new JButton("[Editar]");
        deleteButton = new JButton("[Excluir]");
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
        actionPanel.add(addButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        totalNewsLabel = new JLabel("Total de notícias: 0");
        statusLabel = new JLabel("Status: Sistema pronto");
        statusPanel.add(totalNewsLabel);
        statusPanel.add(statusLabel);

        bottomPanel.add(actionPanel, BorderLayout.WEST);
        bottomPanel.add(statusPanel, BorderLayout.EAST);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        searchButton.addActionListener(e -> atualizarLista(searchField.getText()));
        clearButton.addActionListener(e -> {
            searchField.setText("");
            categoryFilter.setSelectedIndex(0);
            atualizarLista("");
        });
        categoryFilter.addActionListener(e -> atualizarLista(searchField.getText()));

        newsList.addListSelectionListener(e -> {
            boolean isSelected = newsList.getSelectedIndex() != -1;
            editButton.setEnabled(isSelected);
            deleteButton.setEnabled(isSelected);
        });

        addButton.addActionListener(e -> mostrarDialogoNoticia(null));
        editButton.addActionListener(e -> mostrarDialogoNoticia(newsList.getSelectedValue()));
        deleteButton.addActionListener(e -> excluirNoticia());
    }

    private void carregarNoticiasIniciais() {
        todasAsNoticias.add(new Noticia(2, "Brasil vence Copa do Mundo", "Erivan Junior", "Seleção brasileira conquista o título...", "Esportes", LocalDate.of(2025, 9, 28)));
        todasAsNoticias.add(new Noticia(3, "Nova lei ambiental é aprovada", "Maria Vitória", "Congresso aprova nova legislação...", "Política", LocalDate.of(2025, 9, 25)));
        todasAsNoticias.add(new Noticia(4, "Descoberta cura para doença rara", "Igor Alencar", "Cientistas anunciam avanço significativo...", "Saúde", LocalDate.of(2025, 9, 22)));
        todasAsNoticias.add(new Noticia(5, "Economia cresce 5% no trimestre", "Daniela Sales", "PIB surpreende e mostra forte recuperação...", "Economia", LocalDate.of(2025, 9, 20)));
    }

    private void atualizarLista(String filtroTitulo) {
        listModel.clear();
        String categoriaSelecionada = (String) categoryFilter.getSelectedItem();

        List<Noticia> noticiasFiltradas = todasAsNoticias.stream()
            .filter(n -> n.getTitulo().toLowerCase().contains(filtroTitulo.toLowerCase()))
            .filter(n -> "Todas".equals(categoriaSelecionada) || n.getCategoria().equals(categoriaSelecionada))
            .collect(Collectors.toList());

        noticiasFiltradas.forEach(listModel::addElement);
        totalNewsLabel.setText("Total de notícias: " + todasAsNoticias.size());
    }

    private void mostrarDialogoNoticia(Noticia noticia) {
        JTextField tituloField = new JTextField(noticia != null ? noticia.getTitulo() : "", 30);
        JTextField autorField = new JTextField(noticia != null ? noticia.getAutor() : "", 30);
        JComboBox<String> categoriaCombo = new JComboBox<>(new String[]{"Tecnologia", "Esportes", "Política", "Economia", "Saúde"});
        if (noticia != null) {
            categoriaCombo.setSelectedItem(noticia.getCategoria());
        }
        JTextArea conteudoArea = new JTextArea(noticia != null ? noticia.getConteudo() : "", 5, 30);
        JScrollPane contentScrollPane = new JScrollPane(conteudoArea);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("Categoria:"));
        panel.add(categoriaCombo);
        panel.add(new JLabel("Conteúdo:"));
        panel.add(contentScrollPane);

        String title = noticia == null ? "Adicionar Notícia" : "Editar Notícia";
        int result = JOptionPane.showConfirmDialog(this, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (noticia == null) {
                int newId = todasAsNoticias.stream().mapToInt(Noticia::getId).max().orElse(0) + 1;
                Noticia novaNoticia = new Noticia(newId, tituloField.getText(), autorField.getText(), conteudoArea.getText(), (String) categoriaCombo.getSelectedItem(), LocalDate.now());
                todasAsNoticias.add(novaNoticia);
                atualizarStatus("Notícia adicionada com sucesso!");
            } else {
                noticia.setTitulo(tituloField.getText());
                noticia.setAutor(autorField.getText());
                noticia.setCategoria((String) categoriaCombo.getSelectedItem());
                noticia.setConteudo(conteudoArea.getText());
                atualizarStatus("Notícia atualizada com sucesso!");
            }
            atualizarLista(searchField.getText());
        }
    }

    private void excluirNoticia() {
        Noticia selecionada = newsList.getSelectedValue();
        if (selecionada != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esta notícia?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                todasAsNoticias.remove(selecionada);
                atualizarLista(searchField.getText());
                atualizarStatus("Notícia excluída com sucesso!");
            }
        }
    }

    private void atualizarStatus(String message) {
        statusLabel.setText("Status: " + message);

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> statusLabel.setText("Status: Sistema pronto"));
            }
        }, 3000);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GerenciadorDeNoticias ex = new GerenciadorDeNoticias();
            ex.setVisible(true);
        });
    }
}

