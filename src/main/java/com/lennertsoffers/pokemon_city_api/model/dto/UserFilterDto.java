package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.UserFilterConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@UserFilterConstraint
public class UserFilterDto {
    @NotEmpty String filter;

    public String filter() {
        return this.filter;
    }

    public String field() {
        return this.partitions().group(1);
    }

    public String operation() {
        return this.partitions().group(2);
    }

    public String value() {
        return this.partitions().group(3);
    }

    private Matcher partitions() {
        String regex = "(.+)(<=|>=|==|<|>)(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.filter);

        if (!matcher.find()) throw new IllegalArgumentException("The format of the filter is not processable");

        return matcher;
    }

    @Override
    public String toString() {
        return "UserFilterDto{" +
                "filter='" + filter + '\'' +
                '}';
    }
}
