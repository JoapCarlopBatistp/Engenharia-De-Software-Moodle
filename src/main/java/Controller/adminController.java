package Controller;

import Model.sala;

import javax.swing.*;


public class adminController {
    public adminController() {}

    public void cadastrarSala() {
        Integer numero = pedirNumeroInteiro();
        if (numero != null) {
            new sala().cadastrar(numero);
            System.out.println("Sala cadastrada: " + numero);
        } else {
            System.out.println("Erro no cadastro.");
        }
    }

    private static Integer pedirNumeroInteiro() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Digite um número inteiro:", "Entrada", JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                // Usuário cancelou
                return null;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, digite um número inteiro.");
            }
        }
    }
}
