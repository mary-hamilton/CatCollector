package justrosa.catcollector.domain.dto;

public class AuthSuccessDTO {

    private UserDTO userDTO;
    private String jwt;

    public AuthSuccessDTO(UserDTO userDTO, String jwt) {
        this.userDTO = userDTO;
        this.jwt = jwt;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
