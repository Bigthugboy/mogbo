package africa.semicolon.mogbo.dto.Request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePartyRequest {
    private String email;
    private String partyName;
    private String partyLocation;
}
