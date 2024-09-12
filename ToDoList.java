import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ToDoList extends JFrame {
    private DefaultListModel<Projeto> listModel;
    private JList<Projeto> list;
    private List<Projeto> projetos;

    public ToDoList() {
        setTitle("To Do List de Projetos");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            projetos = Persistencia.carregarProjetos();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            projetos = new ArrayList<>();
        }

        listModel = new DefaultListModel<>();
        for (Projeto p : projetos) {
            listModel.addElement(p);
        }

        list = new JList<>(listModel);
        add(new JScrollPane(list), BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(1, 4));
        JButton btnAdicionar = new JButton("Adicionar Projeto");
        JButton btnEditar = new JButton("Editar Projeto");
        JButton btnRemover = new JButton("Remover Projeto");
        JButton btnSortear = new JButton("Sortear Projeto");

        panel.add(btnAdicionar);
        panel.add(btnEditar);
        panel.add(btnRemover);
        panel.add(btnSortear);

        add(panel, BorderLayout.SOUTH);

        // Funcionalidade de Adicionar Projeto
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Nome do Projeto:");
                String descricao = JOptionPane.showInputDialog("Descrição do Projeto:");
                if (nome != null && descricao != null) {
                    Projeto projeto = new Projeto(nome, descricao);
                    projetos.add(projeto);
                    listModel.addElement(projeto);
                    salvarDados();
                }
            }
        });

        // Funcionalidade de Editar Projeto
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    Projeto projetoSelecionado = listModel.getElementAt(selectedIndex);
                    String novoNome = JOptionPane.showInputDialog("Novo Nome do Projeto:", projetoSelecionado.getNome());
                    String novaDescricao = JOptionPane.showInputDialog("Nova Descrição do Projeto:", projetoSelecionado.getDescricao());
                    if (novoNome != null && novaDescricao != null) {
                        projetoSelecionado.setNome(novoNome);
                        projetoSelecionado.setDescricao(novaDescricao);
                        list.repaint();
                        salvarDados();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um projeto para editar.");
                }
            }
        });

        // Funcionalidade de Remover Projeto
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    projetos.remove(selectedIndex);
                    listModel.remove(selectedIndex);
                    salvarDados();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um projeto para remover.");
                }
            }
        });

        // Funcionalidade de Sortear Projeto
        btnSortear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sorteio sorteio = new Sorteio();
                Projeto sorteado = sorteio.sortear(projetos);
                if (sorteado != null) {
                    list.repaint();
                    salvarDados();
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum projeto disponível para sorteio.");
                }
            }
        });
    }

    private void salvarDados() {
        try {
            Persistencia.salvarProjetos(projetos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoList().setVisible(true);
        });
    }
}
