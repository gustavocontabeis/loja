package br.com.coder.loja.servlet;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.com.coder.loja.dao.ProdutoDAO;
import br.com.coder.loja.entity.Arquivo;
import br.com.coder.loja.entity.ImagemProduto;
import br.com.coder.loja.entity.Produto;
import br.com.coder.loja.util.PoolUtil;

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2,	// 2MB 
				 maxFileSize=1024*1024*10,		// 10MB
				 maxRequestSize=1024*1024*50)	// 50MB
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String SAVE_DIR = "uploadFiles";
	
	ProdutoDAO dao = (ProdutoDAO) PoolUtil.getDao(ProdutoDAO.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>UploadServlet</h1>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) 
			fileSaveDir.mkdir();
		
		Long produtoId = null;
		String nome = null;
		List<ImagemProduto> imagensProduto = new ArrayList<ImagemProduto>();

		for (Part part : request.getParts()) {
			
			if("id".equals(part.getName())){
				InputStream inputStream = part.getInputStream();
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);
				produtoId = new Long(new String(buffer));
				continue;
			}
			
			if("nomeDoArquivo".equals(part.getName())){
				InputStream inputStream = part.getInputStream();
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);
				nome = new String(buffer);
				continue;
			}
			
			if(!"image/jpeg".equals(part.getContentType()) && !"image/png".equals(part.getContentType()) )
				continue;
			
			String nomeDoArquivo = extrairNomeDoArquivo(part);
			String string = savePath + File.separator + nomeDoArquivo;
			part.write(string);
			byte[] dados = lerArquivo(string);
			HttpSession session = request.getSession(false);
			session.setAttribute("arquivo-nome", nomeDoArquivo);
			session.setAttribute("arquivo-tamanho", part.getSize());
			session.setAttribute("arquivo-dados", dados);
			
			System.out.println(session.getAttribute("arquivo-nome"));
			System.out.println(session.getAttribute("arquivo-tamanho"));
			System.out.println(session.getAttribute("arquivo-dados"));
			
			ImagemProduto e = new ImagemProduto();
			Arquivo arquivo = new Arquivo();
			arquivo.setDados(dados);
			arquivo.setExtencao(nomeDoArquivo.substring(nomeDoArquivo.indexOf('.')+1));
			arquivo.setId(null);
			arquivo.setNome(nome);
			arquivo.setTamanho(part.getSize());
			e.setArquivo(arquivo);
			imagensProduto.add(e);
				
			new File(string).delete();
		}
		
			Produto obj = new Produto();
			obj.setId(produtoId);
			Produto buscar = dao.buscar(obj);
			for(ImagemProduto ip : imagensProduto){
				ip.setProduto(buscar);
				buscar.getImagens().add(ip);
			}
			dao.salvar(buscar);


	}
	
	public byte[] lerArquivo(String path) throws IOException {
		File file = new File(path);
		FileInputStream in = new FileInputStream(file);
		InputStream is = new BufferedInputStream(in);
		byte[] buffer = null;
		buffer = new byte[is.available()];
		is.read(buffer);
		is.close();
		return buffer;
	}

	private String extrairNomeDoArquivo(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return "";
	}
}