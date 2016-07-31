package com.github.joelws.release.tracker.configuration

import com.github.joelws.release.tracker.conversion.*
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.environment.Environment
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import com.github.joelws.release.tracker.model.artifact.ArtifactModel
import com.github.joelws.release.tracker.model.environment.EnvironmentModel
import com.github.joelws.release.tracker.model.release.ReleaseModel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/*
Copyright 2016 Joel Whittaker-Smith

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
@Configuration
open class AdapterConfig {

    @Bean
    open fun artifactAdapter(): Adapter<Artifact, ArtifactModel> = ArtifactAdapter()

    @Bean
    open fun artifactModelAdapter(): Adapter<ArtifactModel, Artifact> = ArtifactModelAdapter()

    @Bean
    open fun environmentAdapter(): Adapter<Environment, EnvironmentModel> = EnvironmentAdapter()

    @Bean
    open fun environmentModelAdapter(): Adapter<EnvironmentModel, Environment> = EnvironmentModelAdapter()

    @Bean
    open fun releaseAdapter(): Adapter<Release, ReleaseModel> = ReleaseAdapter()

    @Bean
    open fun releaseModelAdapter(): Adapter<ReleaseModel, Release> = ReleaseModelAdapter()

}
