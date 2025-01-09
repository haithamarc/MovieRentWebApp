package dvd_rental_film.boundary;

import dvd_rental_film.json.FilmJson;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Setter
@Getter
@Named
@SessionScoped
public class FilmJSF implements Serializable {
    // das Wert ändert sich  immer ensprechned des Bedarf
    private List<FilmJson> films;
    // Dummy : das Wert ändert sich  immer ensprechned des Bedarf
    private FilmJson aktuellerFilm;
    private List<FilmJson> rows;
    private List<FilmJson> selectedfilms;
    private int id ;
    private Jsonb binding = JsonbBuilder.create();
    private Client client = ClientBuilder.newClient();
    private String customerBase;
    private String filmBase;
    private String storeBase;

    public FilmJSF() throws IOException {
        films = new ArrayList<>();
        aktuellerFilm = new FilmJson();
        selectedfilms = new ArrayList<>();
        Properties prop = new Properties();
        if(this.getClass().getResourceAsStream("/urlsLocal.properties") != null) {
            prop.load(this.getClass().getResourceAsStream("/urlsLocal.properties"));   
        } else if(this.getClass().getResourceAsStream("/urlsDocker.properties") != null) {
            prop.load(this.getClass().getResourceAsStream("/urlsDocker.properties"));
        }
        customerBase = prop.getProperty("customerBase");
        filmBase = prop.getProperty("filmBase");
        storeBase = prop.getProperty("storeBase");
        
    }
    /* 
    Logger  logger ;
    public List<FilmJson> getAll() throws MalformedURLException {
        
        dt.setWrappedData(stream(new URL("http://localhost:8080/dvd-rental-film/resources"))) ;
        return dt;
    }
    public List<Film> stream(URL url) {
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            ObjectMapper mapper = new ObjectMapper();
            List<Film>  filmList = Arrays.asList(mapper.readValue(json.toString(), Film[].class));
            return filmList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String  aendern(){
        //getRowData() Return the object associated with the current row index from Table.
        //---aktuellerFilm= films.getRowData();
      //int id= films.getRowData().getFilmId();
        return "modify?faces-redirect=true";
    }*/
    public String ausleihen(int fId) {
        aktuellerFilm = films.stream().filter(f -> f.id == fId).findAny().orElse(null);
        if(aktuellerFilm != null) selectedfilms.add(aktuellerFilm);
        return "sfilm?faces-redirect=true";
    }

    public boolean istAusgeliehen(int fId) {
        return selectedfilms.stream().anyMatch(f -> f.id == fId);
    }
    
    public String ausleihungAbschließen(){
        return "index?faces-redirect=true";
    }

    @PostConstruct
    public void init()  {
        System.out.println(customerBase);
       WebTarget target = client.target("http://localhost:8080/dvd-rental-film/resources/films");
       films = binding.fromJson(target.request().get().readEntity(String.class), new ArrayList<FilmJson>(){}.getClass().getGenericSuperclass());
    }
   /* die rechtige daten Quelle
    public DataModel<Film> getComedians() throws MalformedURLException {
        films.setWrappedData(stream(new URL("http://localhost:8080/dvd-rental-film/resources/films"))) ;
         } */

   public String loeschen(int fId, String seite) throws IOException {
       selectedfilms.removeIf(f -> f.id == fId);
       return seite + "?faces-redirect=true";
   }
   
    public String ausleihWagen () {
        return "ausleihwagen?faces-redirect=true";
    }
    /*
    public String neuanlage () {
        aktuellerFilm = new Film();
       return "modify?faces-redirect=true";
        }
    public String speichern() {
       int id = aktuellerFilm.getFilmId();
      //soll auch  post request  with aktuellerFilm;
        return "sfilm?faces-redirect=true";
    }

    public String suchNachId() throws IOException {
        //soll   get request  with aktuellerFilm;
        URL url = new URL("http://localhost:8080/dvd-rental-film/resources/films/"+ id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        // Read the response from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        // Parse the response as JSON
        var jsonReader = Json.createReader(new StringReader(response.toString()));
        var filmAsJson = jsonReader.readObject();
    // Create a Gson instance
        Gson gson = new Gson();

    // Define the class of the object you want to create
         aktuellerFilm = gson.fromJson(filmAsJson.toString(), Film.class);
        List<Film> flist = new ArrayList<>();
        flist.add(aktuellerFilm) ;
        films.setWrappedData(flist) ;
        return "filmResult?faces-redirect=true";
    }

    public String getAllfilm() throws IOException {
        //soll   get request  with aktuellerFilm;
        URL url = new URL("http://localhost:8080/dvd-rental-film/resources/films");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        // Read the response from the server
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        // Parse the response as JSON
        var jsonReader = Json.createReader(new StringReader(response.toString()));
        var filmAsJson = jsonReader.readObject();
        // Create a Gson instance
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Film>>(){}.getType();
        // convert list of JSON to list of java objects
        List<Film> flist = gson.fromJson((Reader) jsonReader, listType);
        films.setWrappedData(flist) ;
        return "filmResult?faces-redirect=true";
    }

    public  void addRow(Film fm,DataModel<Film> dtm) {
        rows = (List<Film>)dtm.getWrappedData();
     //   Object[] newArray = new Object[rows.size() + 1];
      //  System.arraycopy(rows, 0, newArray, 0, rows.size());
        //newArray[rows.size()] = fm;
        rows.add(fm);
        dtm.setWrappedData(rows);
    }

   /* //to send url request
    public HttpURLConnection getHttpConnection(String url, String type){
        URL uri = null;
        HttpURLConnection con = null;
        try{
            uri = new URL(url);
            con = (HttpURLConnection) uri.openConnection();
            con.setRequestMethod(type); //type: POST, PUT, DELETE, GET
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setConnectTimeout(60000); //60 secs
            con.setReadTimeout(60000); //60 secs
            con.setRequestProperty("Accept-Encoding", "Your Encoding");
            con.setRequestProperty("Content-Type", "Your Encoding");
        }catch(Exception e){
            logger.info( "connection i/o failed" );
        }
        return con;
    }
    public void yourmethod(String url, String type, String reqbody){
        HttpURLConnection con = null;
        String result = null;
        try {
            con = conUtil.getHttpConnection( url , type);
            //you can add any request body here if you want to post
            if( reqbody != null){
                con.setDoInput(true);
                con.setDoOutput(true);
                DataOutputStream out = new  DataOutputStream(con.getOutputStream());
                out.writeBytes(reqbody);
                out.flush();
                out.close();
            }
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String temp = null;
            StringBuilder sb = new StringBuilder();
            while((temp = in.readLine()) != null){
                sb.append(temp).append(" ");
            }
            result = sb.toString();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
//result is the response you get from the remote side
    } */
    
}
