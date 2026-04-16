package pett.iat.domain.arma.dtos;

import java.sql.Date;
import jakarta.validation.constraints.NotNull;
import pett.iat.enums.LocalRegistroArma;
import pett.iat.enums.SentidoRaiasArma;
import pett.iat.enums.TipoAlmaArma;
import pett.iat.enums.TipoUsoArma;

public record ArmaUpdateDto(

            @NotNull Long id,
            LocalRegistroArma localRegistroArma,

            String numeroCraf,

            String numeroSerie,

            String numeroCano,

            String modelo,

            Long calibreId, // ID para buscar a entidade Calibre

            Long marcaId, // ID para buscar a entidade Marca

            TipoAlmaArma tipoAlmaArma,

            TipoUsoArma tipoUsoArma,

            Integer numeroRaias,

            SentidoRaiasArma sentidoRaiasArma,

            Boolean vencimentoIndeterminado,

            Date dataVencimentoCraf) {
}
