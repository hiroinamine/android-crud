package br.com.caelum.cadastro;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5193 on 08/04/15.
 */
public class FormularioHelper {

    public FormularioHelper(FormularioActivity activity){
        this.aluno = new Aluno();

        // TODO: fazer via reflection

        /*
        Class cls = Class.forName("br.com.caelum.cadastro.modelo.Aluno");
        Method methlist[] = cls.getDeclaredMethods();

        for (int i = 0; i < methlist.length; i++) {
            Method m = methlist[i];
            System.out.println("nome = " + m.getName());
            System.out.println("membro da classe = " + m.getDeclaringClass());
            System.out.println("modificador = " + Modifier.toString(m.getModifiers()));
            Class pvec[] = m.getParameterTypes();

            for (int j =Class 0; j < pvec.length; j++)
                System.out.println("parâmetro #" + j + " " + pvec[j]);

            Class evec[] = m.getExceptionTypes();
            for (int j = 0; j < evec.length; j++)
                System.out.println("exceção #" + j + " " + evec[j]);

            System.out.println("tipo de retorno = " + m.getReturnType());
            System.out.println("-----");
        }
        */

        this.nome = (EditText) activity.findViewById(R.id.formulario_nome);
        this.telefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        this.site = (EditText) activity.findViewById(R.id.formulario_site);
        this.nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        this.endereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        this.foto = (ImageView) activity.findViewById(R.id.formulario_foto);
        this.fotoButton = (Button) activity.findViewById(R.id.formulario_foto_botao);
    }

    public Button getFotoButton(){
        return fotoButton;
    }

    public void carregaImagem(String localArquivoFoto){
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);
        foto.setImageBitmap(imagemFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    public Aluno pegaAlunoDoFormulario(){
        // TODO: fazer via reflection
        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        aluno.setEndereco(endereco.getText().toString());
        aluno.setCaminhoFoto((String)foto.getTag());
        return this.aluno;
    }

    public void colocaNoFormulario(Aluno aluno){
        // TODO: fazer via reflection
        this.aluno = aluno;

       /* String sClassName = "br.com.caelum.cadastro.modelo.Aluno";
        try {
            Class classToInvestigate = Class.forName(sClassName);
            Method[] aClassMethods = classToInvestigate.getDeclaredMethods();
            for (Method m : aClassMethods){
                m.
            }

            // Dynamically do stuff with this class
            // List constructors, fields, methods, etc.

        } catch (ClassNotFoundException e) {
            // Class not found!
        } catch (Exception e) {
            // Unknown exception
        }
*/

        this.nome.setText(aluno.getNome());
        this.telefone.setText(aluno.getTelefone());
        this.site.setText(aluno.getSite());
        this.nota.setProgress(aluno.getNota().intValue());
        this.endereco.setText(aluno.getEndereco());

        if (aluno.getCaminhoFoto() != null)
            carregaImagem(aluno.getCaminhoFoto());
    }


    public boolean temNome(){
        return !nome.getText().toString().isEmpty();
    }

    public void mostraErro(){
        nome.setError("Campo nome nao ppode ser vazio");
    }

    private Aluno aluno;

    private EditText nome;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private EditText endereco;
    private ImageView foto;
    private Button fotoButton;
}
