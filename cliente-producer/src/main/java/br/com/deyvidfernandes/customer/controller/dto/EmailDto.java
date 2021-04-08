package br.com.deyvidfernandes.customer.controller.dto;

public class EmailDto {
    private int clienteId;
    private String email;

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
