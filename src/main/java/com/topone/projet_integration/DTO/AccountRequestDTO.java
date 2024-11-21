package com.topone.projet_integration.DTO;

public class AccountRequestDTO {
    private boolean isAccepted;

    public AccountRequestDTO() {
    }

    public boolean getIsAccepted() { // Vérifiez que ce getter est bien nommé !
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) { // Assurez-vous que le setter utilise le bon nom
        this.isAccepted = isAccepted;
    }
}

