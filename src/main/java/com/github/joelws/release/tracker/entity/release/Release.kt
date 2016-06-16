package com.github.joelws.release.tracker.entity.release

import com.github.joelws.release.tracker.entity.artifact.Artifact
import java.util.*
import javax.persistence.*

open @Entity class Release(@Id
                           @Column(name = "name")
                           var name: String? = null,

                           @ManyToMany(
                                   fetch = javax.persistence.FetchType.EAGER)
                           var artifacts: List<Artifact> = ArrayList<Artifact>(),

                           @ManyToMany(
                                   fetch = javax.persistence.FetchType.EAGER)
                           @JoinTable(name = "release_hotfix")
                           @JoinColumn(name = "hotfix_name")
                           var hotfixes: Set<Release> = HashSet<Release>())

