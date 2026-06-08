package pett.iat.pix;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

import br.com.competeaqui.pix.DadosEnvioPix;
import br.com.competeaqui.pix.QRCodePix;

@Service
public class MontarPix {

   public String dadosEnviados() {

      DadosEnvioPix dados = new DadosEnvioPix(
            "Peterson",
            "01027040900",
            new BigDecimal("10.50"),
            "Guarapuava",
            "Pagamento teste");

      QRCodePix qrCodePix = new QRCodePix(dados);

      return qrCodePix.generate();
   }
}