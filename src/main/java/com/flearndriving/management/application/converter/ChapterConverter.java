package com.flearndriving.management.application.converter;

import com.flearndriving.management.application.dto.request.ChapterRequest;
import com.flearndriving.management.application.dto.response.ChapterResponse;
import com.flearndriving.management.application.entities.Chapter;
import com.flearndriving.management.application.entities.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChapterConverter {

    @Resource
    private DocumentConverter documentConverter;

    public Chapter buildChapter(final ChapterRequest source) {

        return Chapter.builder()
                .name(source.getName())
                .content(source.getContent())
                .description(source.getDescription())
                .listImages(this.buildDocuments(source.getImages()))
                .build();
    }

    public void bukChapter(Chapter target, ChapterRequest source) {
        target.setName(source.getName());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setListImages(this.buildDocuments(source.getImages()));
    }

    private List<Document> buildDocuments(MultipartFile[] source) {
        List<Document> documents = new ArrayList<>();
        for (MultipartFile doc : source) {
            documents.add(documentConverter.buildDocument(doc));
        }
        return documents;
    }

    public ChapterResponse buildChapterResponse(final Chapter source) {

        return ChapterResponse.builder()
                .name(source.getName())
                .content(source.getContent())
                .description(source.getDescription())
                .build();
    }
}
