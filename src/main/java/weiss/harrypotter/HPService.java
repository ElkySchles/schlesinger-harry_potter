package weiss.harrypotter;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

import java.util.List;

public interface HPService {
    @GET("Spells")
    Observable<List<Spell>> getSpells();
}
