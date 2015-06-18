package com.ai.tris.server.db.utils;

import com.ai.tris.server.AppContextFactory;
import com.ai.tris.server.db.IdGenerator;
import com.ai.tris.server.db.mysql.MysqlIdGenerator;
import com.ai.tris.server.orm.impl.SysSequencesBean;
import com.ai.tris.server.service.interfaces.ITrisCommService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Id generate util.
 * <p/>
 * Created by Sam on 2015/6/16.
 */
public class IdGenUtil {

    private transient static Log log = LogFactory.getLog(IdGenUtil.class);

    // use mysql id generator default
    private static String GEN_IMPL_CLASS = MysqlIdGenerator.class.getName();

    private static Map<String, IdGenerator> ID_GS = new ConcurrentHashMap<String, IdGenerator>();

    static {
        initIdGens();
    }

    public static long getNewId(String tableName) {
        IdGenerator idGenerator = ID_GS.get(tableName + "$SEQ");
        if (null == idGenerator) {
            throw new UnknownError(String.format("Unknown table name %s", tableName));
        }
        return idGenerator.getNewId();
    }

    private static void initIdGens() {
        ITrisCommService trisCommService =
                AppContextFactory.getAppContext().getBean(ITrisCommService.class.getName(), ITrisCommService.class);
        List<SysSequencesBean> sequences = trisCommService.getSysSequences();
        if (null != sequences && sequences.size() > 0) {
            for (SysSequencesBean sequence : sequences) {
                SysSequencesBean updatedSysSeq =
                        trisCommService.getAndUpdateSequence(sequence.getString(SysSequencesBean.S_SEQUENCE_NAME));
                try {
                    Constructor constructor =
                            Class.forName(GEN_IMPL_CLASS).getConstructor(long.class, String.class, int.class);
                    IdGenerator idGenerator = (IdGenerator) constructor.newInstance(
                            updatedSysSeq.get(SysSequencesBean.S_LAST_NUMBER),
                            updatedSysSeq.getString(SysSequencesBean.S_SEQUENCE_NAME),
                            updatedSysSeq.getInt(SysSequencesBean.S_JVM_STEP_BY));
                    ID_GS.put(sequence.getString(SysSequencesBean.S_SEQUENCE_NAME), idGenerator);
                } catch (Exception exception) {
                    log.error("Init id generator failed.", exception);
                }
            }
        }
    }
}
