package com.example.demo.rating;



import java.util.List;
import java.util.Map;

public interface RateService {
    public Map<String,String> saveRating(RateRequest request);
    public double averageRating(Long ground_id);
    public Rate getRating(Long book_id);
    public List<Rate> getFutsalRating(Long futsalId);
}
