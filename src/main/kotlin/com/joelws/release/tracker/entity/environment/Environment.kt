package com.joelws.release.tracker.entity.environment

import com.joelws.release.tracker.entity.release.Release
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

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
@Entity
data class Environment(@Id
                       @Column(name = "env_name")
                       var environmentName: String? = null,
                       @OneToOne(targetEntity = Release::class)
                       val release: Release? = null)
