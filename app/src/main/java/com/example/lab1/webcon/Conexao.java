package com.example.lab1.webcon;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexao {

    public static String postDados(String urlUsuario, String parametrosUsuario) {
        URL url;
        HttpURLConnection connection = null;
        try {

            url = new URL(urlUsuario);
            //Abre conexão com a url passada
            connection = (HttpURLConnection) url.openConnection();
            //Determina que as informações irão via post
            connection.setRequestMethod("POST");
            //Determina o modo de codificação, tratamento de caracteres, linguagem
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(parametrosUsuario.getBytes().length));
            connection.setRequestProperty("Content-Language", "pt-BR");

            //Invlida o cache
            connection.setUseCaches(false);
            //Ativa entrada e saida para os dados, ou seja tanto poderá receber como enviar dados via POST
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Envia as informações do usuário
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(parametrosUsuario);
            dataOutputStream.flush();
            dataOutputStream.close();


            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            //Receber uma resposta da transação, positiva ou negativa
            String linha;
            StringBuffer resposta = new StringBuffer();

            //Processa(Monta) os dados oriundos da resposta
            while ((linha = bufferedReader.readLine()) != null) {
                resposta.append(linha);
                resposta.append('\r');
            }

            //Fecha a transação e retorna a resposta
            bufferedReader.close();
            return resposta.toString();

        } catch (Exception erro) {

            return null;
        } finally {
            //Fecha a conexão
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
