package umc.spring.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPrefer is a Querydsl query type for UserPrefer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPrefer extends EntityPathBase<UserPrefer> {

    private static final long serialVersionUID = 1615227161L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPrefer userPrefer = new QUserPrefer("userPrefer");

    public final umc.spring.category.domain.QCategory category;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final umc.spring.user.domain.QUser user;

    public QUserPrefer(String variable) {
        this(UserPrefer.class, forVariable(variable), INITS);
    }

    public QUserPrefer(Path<? extends UserPrefer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPrefer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPrefer(PathMetadata metadata, PathInits inits) {
        this(UserPrefer.class, metadata, inits);
    }

    public QUserPrefer(Class<? extends UserPrefer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new umc.spring.category.domain.QCategory(forProperty("category")) : null;
        this.user = inits.isInitialized("user") ? new umc.spring.user.domain.QUser(forProperty("user")) : null;
    }

}

