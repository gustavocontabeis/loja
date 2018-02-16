package br.com.coder.loja.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import br.com.coder.loja.dao.ArquivoDAO;
import br.com.coder.loja.entity.Arquivo;
import br.com.coder.loja.util.PoolUtil;

@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Map<String, String> mimeTypes;

	private ArquivoDAO arquivoDAO = (ArquivoDAO) PoolUtil.getDao(ArquivoDAO.class);;
	
	static {
		mimeTypes = new HashMap<>();
		mimeTypes.put("png", "image/png");
		mimeTypes.put("jpg", "image/jpg");
		mimeTypes.put("jpeg", "image/jpg");
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("id");
		if(StringUtils.isNotBlank(parameter)){
			Arquivo arquivo = arquivoDAO.buscar(Arquivo.class, Long.valueOf(parameter));
			if(arquivo != null){
				response.setContentType(mimeType(arquivo.getExtencao()));
				
				byte[] imagem = arquivo.getDados();
				ByteInputStream bis = new ByteInputStream(imagem, imagem.length);
				ServletOutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[1024];
				int tamBuf = 0;
				while((tamBuf = bis.read(buffer))!= -1){
					outputStream.write(buffer, 0, tamBuf);
				}
				
			}
		}
	}

	private String mimeType(String extencao) {
		return mimeTypes.get(extencao);
	}

}
