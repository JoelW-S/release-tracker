package com.joelws.release.tracker.entity.release

import com.joelws.release.tracker.entity.artifact.Artifact
import java.util.*
import javax.persistence.*

@Entity
data class Release(@Id
                   @Column(name = "name")
                   var name: String = "",

                   @ManyToMany(
                           fetch = javax.persistence.FetchType.EAGER,
                           targetEntity = Artifact::class
                   )
                   var artifacts: MutableList<Artifact> = ArrayList<Artifact>(),

                   @ManyToMany(
                           fetch = FetchType.EAGER,
                           targetEntity = Release::class)
                   @JoinTable(name = "release_hotfix")
                   @JoinColumn(name = "hotfix_name")
                   var hotfixes: MutableSet<Release> = HashSet<Release>())

