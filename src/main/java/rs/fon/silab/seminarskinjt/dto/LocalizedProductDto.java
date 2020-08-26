/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.dto;

/**
 *
 * @author Bozidar
 */
public class LocalizedProductDto {

    private LocalizedIdDto localizedId;

    private String description;

    public LocalizedProductDto() {
    }

    public LocalizedProductDto(LocalizedIdDto localizedId, String description) {
        this.localizedId = localizedId;
        this.description = description;
    }

    public LocalizedIdDto getLocalizedId() {
        return localizedId;
    }

    public void setLocalizedId(LocalizedIdDto localizedId) {
        this.localizedId = localizedId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
