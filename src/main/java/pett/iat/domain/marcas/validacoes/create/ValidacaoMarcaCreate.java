package pett.iat.domain.marcas.validacoes.create;
import pett.iat.domain.marcas.dtos.MarcaCreateDto;

public interface ValidacaoMarcaCreate {

   void validar(MarcaCreateDto marca);
}
