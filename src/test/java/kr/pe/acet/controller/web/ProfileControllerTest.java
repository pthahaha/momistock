package kr.pe.acet.controller.web;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileControllerTest {

    @Test
    public void real_profile_조회(){
        // given
        String expectedPfrofile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedPfrofile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real_db");
        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // Then
        assertThat(profile).isEqualTo(expectedPfrofile);

    }

    @Test
    public void real_profile이_없으면_첫번째가_조회된다(){
        //given
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();

        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void active_profile이없으면_default가_조회된다(){
        //given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }
}