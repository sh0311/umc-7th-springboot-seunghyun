package umc.spring.region.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegion is a Querydsl query type for Region
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegion extends EntityPathBase<Region> {

    private static final long serialVersionUID = -1746074462L;

    public static final QRegion region = new QRegion("region");

    public final umc.spring.global.QBaseEntity _super = new umc.spring.global.QBaseEntity(this);

    public final NumberPath<Integer> completeNum = createNumber("completeNum", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<umc.spring.mission.domain.Mission, umc.spring.mission.domain.QMission> missions = this.<umc.spring.mission.domain.Mission, umc.spring.mission.domain.QMission>createList("missions", umc.spring.mission.domain.Mission.class, umc.spring.mission.domain.QMission.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> scoreSum = createNumber("scoreSum", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QRegion(String variable) {
        super(Region.class, forVariable(variable));
    }

    public QRegion(Path<? extends Region> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegion(PathMetadata metadata) {
        super(Region.class, metadata);
    }

}

