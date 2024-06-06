package rca.ac.supermarket.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import rca.ac.supermarket.enums.ResponseType;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {
    private T payload;
    private ResponseType responseType;
}
