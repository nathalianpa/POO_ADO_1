/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathalia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GerenciarPib gerenciar_pib = new GerenciarPib("./src/pib.txt", "./src/regioes.txt");
	gerenciar_pib.gerarListas();
	gerenciar_pib.somarPibs();
	gerenciar_pib.pibsEstados();
	gerenciar_pib.pibsRegioes();
    } 
}