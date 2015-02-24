import java.util.ArrayList;

public class FourDimArray {
    private ArrayList<String> subjects = new ArrayList<String>();
    private ArrayList<String> medias = new ArrayList<String>();
    private ArrayList<ArrayList<String>> stimuli = new ArrayList<ArrayList<String>>();
    private ArrayList<String> statistics = new ArrayList<String>();
    private ArrayList<ArrayList<ArrayList<ArrayList<Double>>>> data = new ArrayList<ArrayList<ArrayList<ArrayList<Double>>>>();

    public ArrayList<String> getSubjects() { // returns an arraylist of subjects
        return subjects;
    }
    
    public ArrayList<String> getMedias() { // returns an arraylist of medias
        return medias;
    }
    
    public ArrayList<ArrayList<String>> getStimuli() { // returns a two dim arraylist of stimuli
        return stimuli;
    }
    
    public ArrayList<String> getStimuli(String media) { // returns an arraylist of stimuli for a given media
        int mediaIndex = medias.indexOf(media);
        if (mediaIndex == -1) {
            return null;
        }
        return stimuli.get(mediaIndex);
    }
    
    public ArrayList<String> getStatistics() { // returns an arraylist of statistics
        return statistics;
    }
    
    public void set(String subject, String media) { // for use with blank or missing sheets
        int subjectIndex, mediaIndex;
        subjectIndex = subjects.indexOf(subject);
        if (subjectIndex == -1) {
            subjects.add(subject);
            subjectIndex = subjects.size() - 1;
        }
        mediaIndex = medias.indexOf(media);
        if (mediaIndex == -1) {
            medias.add(media);
            stimuli.add(new ArrayList<String>());
            mediaIndex = medias.size() - 1;
        }
        while (data.size() <= subjectIndex) {
            data.add(new ArrayList<ArrayList<ArrayList<Double>>>());
        }
        while (data.get(subjectIndex).size() <= mediaIndex) {
            data.get(subjectIndex).add(new ArrayList<ArrayList<Double>>());
        }
    }

    public void set(String subject, String media, String stimulus, String statistic, Double value) { // for use with setting values or null(blank)
        int subjectIndex, mediaIndex, stimuliIndex, statisticIndex;
        subjectIndex = subjects.indexOf(subject);
        if (subjectIndex == -1) {
            subjects.add(subject);
            subjectIndex = subjects.size() - 1;
        }
        mediaIndex = medias.indexOf(media);
        if (mediaIndex == -1) {
            medias.add(media);
            stimuli.add(new ArrayList<String>());
            mediaIndex = medias.size() - 1;
        }
        stimuliIndex = stimuli.get(mediaIndex).indexOf(stimulus);
        if (stimuliIndex == -1) {
            stimuli.get(mediaIndex).add(stimulus);
            stimuliIndex = stimuli.get(mediaIndex).size() - 1;
        }
        statisticIndex = statistics.indexOf(statistic);
        if (statisticIndex == -1) {
            statistics.add(statistic);
            statisticIndex = statistics.size() - 1;
        }
        while (data.size() <= subjectIndex) {
            data.add(new ArrayList<ArrayList<ArrayList<Double>>>());
        }
        while (data.get(subjectIndex).size() <= mediaIndex) {
            data.get(subjectIndex).add(new ArrayList<ArrayList<Double>>());
        }
        while (data.get(subjectIndex).get(mediaIndex).size() <= stimuliIndex) {
            data.get(subjectIndex).get(mediaIndex).add(new ArrayList<Double>());
        }
        while (data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).size() <= statisticIndex) {
            data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).add(null);
        }
        data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).set(statisticIndex, value);
    }
    
    public Double get(String subject, String media, String stimulus, String statistic) { // for use getting values
        int subjectIndex, mediaIndex, stimuliIndex, statisticIndex;
        subjectIndex = subjects.indexOf(subject);
        if ((subjectIndex == -1) || (data.size() <= subjectIndex)) {
            return null;
        }
        mediaIndex = medias.indexOf(media);
        if ((mediaIndex == -1) || (data.get(subjectIndex).size() <= mediaIndex)) {
            return null;
        }
        stimuliIndex = stimuli.get(mediaIndex).indexOf(stimulus);
        if ((stimuliIndex == -1) || (data.get(subjectIndex).get(mediaIndex).size() <= stimuliIndex)) {
            return null;
        }
        statisticIndex = statistics.indexOf(statistic);
        if ((statisticIndex == -1) || (data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).size() <= statisticIndex)) {
            return null;
        }
        return data.get(subjectIndex).get(mediaIndex).get(stimuliIndex).get(statisticIndex);
    }
}